package com.intrbiz.discuss.mailer.job;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.model.DiscussPost;
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.FetchedEmail;
import com.intrbiz.discuss.model.User;

public class CreatedPostMessage extends MailJob
{   
    private Discussion discussion;
    
    private DiscussThread thread;
    
    private DiscussPost post;
    
    private User fromUser;
    
    private List<User> to;
    
    private String inReplyTo;
    
    public CreatedPostMessage()
    {
        super();
    }

    public CreatedPostMessage(Discussion discussion, DiscussThread thread, DiscussPost post)
    {
        super();
        this.discussion = discussion;
        this.thread = thread;
        this.post = post;
        this.fromUser = this.post.getFromUser();
    }
    
    @Override
    public boolean multiMessage()
    {
        return true;
    }
    
    @Override
    public void begin()
    {
        try (DiscussDB db = DiscussDB.connect())
        {
            this.to = db.getActiveUsersInDiscussion(this.discussion.getId());
            // filter out the from user
            for (Iterator<User> i = this.to.iterator(); i.hasNext(); )
            {
                if (this.fromUser.getEmail().equals(i.next().getEmail())) i.remove();
            }
            // in reply to
            this.inReplyTo = "<00000000-0000-0000-0000-000000000000." + this.thread.getThreadId() + "@" + this.discussion.getId() + ">";
            // TODO: add specific DB method
            List<FetchedEmail> emailsInThread = db.getFetchedEmailsByDiscussionThread(this.discussion.getId(), this.thread.getThreadId());
            if (emailsInThread.size() > 0)
            {
                this.inReplyTo = emailsInThread.get(emailsInThread.size() -1).getMessageId();
            }
        }
    }
    
    public Message message(Session session) throws Exception
    {
        if (this.to.isEmpty()) return null;
        User user = this.to.remove(0);
        // build the message
        Message message = new MimeMessage(session) {
            @Override
            protected void updateMessageID() throws MessagingException
            {
                // do not alter the message id
            }
        };
        // from
        message.setFrom(new InternetAddress(this.fromUser.getEmail(), this.fromUser.getDisplayName()));
        // to address
        message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail(), user.getDisplayName()));
        // headers
        message.setHeader("Reply-To", new InternetAddress(this.discussion.getEmail(), this.discussion.getSummary()).toString());
        message.setHeader("X-Discuss-Discussion-Name", this.discussion.getName());
        message.setHeader("X-Discuss-Discussion-Id", this.discussion.getId().toString());
        message.setHeader("X-Discuss-Thread-Id", this.thread.getThreadId().toString());
        message.setSentDate(new Date());
        message.setHeader("Message-ID", "<" + this.post.getPostId() + "." + this.thread.getThreadId() + "@" + this.discussion.getId() + ">");
        // threading
        message.setHeader("In-Reply-To",  this.inReplyTo);
        message.setHeader("References",  "<00000000-0000-0000-0000-000000000000." + this.thread.getThreadId() + "@" + this.discussion.getId() + ">");
        // content
        message.setSubject(this.post.getSummary());
        message.setContent(this.post.getContent(), "text/plain");
        // done
        return message;
    }
    
    @Override
    public void end()
    {
    }
}

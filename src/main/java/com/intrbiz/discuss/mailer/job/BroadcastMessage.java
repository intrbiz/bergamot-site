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
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.User;

public class BroadcastMessage extends MailJob
{
    private Discussion discussion;
    
    private DiscussThread thread;
    
    private User from;
    
    private String messageId;
    
    private String inReplyTo;
    
    private String subject;
    
    private String content;
    
    // state
    
    private List<User> to;
    
    public BroadcastMessage()
    {
        super();
    }

    public BroadcastMessage(Discussion discussion, DiscussThread thread, User from, String messageId, String inReplyTo, String subject, String content)
    {
        super();
        this.discussion = discussion;
        this.thread = thread;
        this.from = from;
        this.messageId = messageId;
        this.inReplyTo = inReplyTo;
        this.subject = subject;
        this.content = content;
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
                if (this.from.getEmail().equals(i.next().getEmail())) i.remove();
            }
        }
    }
    
    @Override
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
        message.setFrom(new InternetAddress(this.from.getEmail(), this.from.getDisplayName()));
        // to address
        message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail(), user.getDisplayName()));
        // headers
        message.setHeader("Reply-To", new InternetAddress(this.discussion.getEmail(), this.discussion.getSummary()).toString());
        message.setHeader("X-Discuss-Discussion-Name", this.discussion.getName());
        message.setHeader("X-Discuss-Discussion-Id", this.discussion.getId().toString());
        message.setHeader("X-Discuss-Thread-Id", this.thread.getThreadId().toString());
        message.setSentDate(new Date());
        message.setHeader("Message-ID", this.messageId);
        if (this.inReplyTo != null) message.setHeader("In-Reply-To", this.inReplyTo);
        message.setHeader("References",  "<00000000-0000-0000-0000-000000000000." + this.thread.getThreadId() + "@" + this.discussion.getId() + ">");
        // content
        message.setSubject(this.subject);
        message.setContent(this.content, "text/plain");
        // done
        return message;
    }
    
    @Override
    public void end()
    {
    }
}

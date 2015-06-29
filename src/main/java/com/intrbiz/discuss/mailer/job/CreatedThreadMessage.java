package com.intrbiz.discuss.mailer.job;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.User;

public class CreatedThreadMessage extends MailJob
{   
    private Discussion discussion;
    
    private DiscussThread thread;
    
    private User fromUser;
    
    private List<User> to;
    
    public CreatedThreadMessage()
    {
        super();
    }

    public CreatedThreadMessage(Discussion discussion, DiscussThread thread)
    {
        super();
        this.discussion = discussion;
        this.thread = thread;
        this.fromUser = this.thread.getFromUser();
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
        message.setHeader("Message-ID",  "<00000000-0000-0000-0000-000000000000." + this.thread.getThreadId() + "@" + this.discussion.getId() + ">");
        // content
        message.setSubject("New thread: " + this.thread.getSummary());
        message.setContent(this.buildContent(), "text/plain");
        // done
        return message;
    }
    
    private String buildContent()
    {
        StringBuilder content = new StringBuilder();
        content.append("The thread " + this.thread.getSummary()+ " has been created in the discussion " + this.discussion.getSummary() + " by " + this.thread.getFromUser().getDisplayName() + "\r\n");
        content.append("\r\n");
        content.append(this.thread.getDescription() + "\r\n");
        return content.toString();
    }
    
    @Override
    public void end()
    {
    }
}

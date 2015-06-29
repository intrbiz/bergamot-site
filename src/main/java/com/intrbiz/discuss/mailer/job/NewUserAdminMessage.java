package com.intrbiz.discuss.mailer.job;

import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.User;

public class NewUserAdminMessage extends MailJob
{   
    private User user;
    
    private List<User> to;
    
    public NewUserAdminMessage()
    {
        super();
    }

    public NewUserAdminMessage(User user)
    {
        super();
        this.user = user;
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
            this.to = db.getUsers();
            // filter out non-admins
            for (Iterator<User> i = this.to.iterator(); i.hasNext(); )
            {
                if (! "admin".equals(i.next().getRole())) i.remove();
            }
        }
    }
    
    public Message message(Session session) throws Exception
    {
        if (this.to.isEmpty()) return null;
        User user = this.to.remove(0);
        // build the message
        Message message = new MimeMessage(session);
        // from
        // TODO
        message.setFrom(new InternetAddress("discuss@bergamot-monitoring.org", "Bergamot Discuss"));
        // to address
        message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail(), user.getDisplayName()));
        // content
        message.setSubject("New User Registration");
        message.setContent(this.buildContent(user), "text/plain");
        // done
        return message;
    }
    
    private String buildContent(User to)
    {
        StringBuilder content = new StringBuilder();
        content.append("Hi ").append(to.getDisplayName()).append("\r\n\r\n");
        content.append("A new user has registered with Discuss:\r\n");
        content.append("Name: ").append(this.user.getDisplayName()).append("\r\n");
        content.append("Email: ").append(this.user.getEmail()).append("\r\n");
        content.append("Discussions:\r\n\r\n");
        for (Discussion discussion : this.user.getJoinedDiscussions())
        {
            content.append(" * ").append(discussion.getSummary()).append(" - ").append(discussion.getEmail()).append("\r\n");
        }
        content.append("\r\n");
        return content.toString();
    }
    
    @Override
    public void end()
    {
    }
}

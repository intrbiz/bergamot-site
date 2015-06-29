package com.intrbiz.discuss.mailer.job;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.User;

public class VerifyUserMessage extends MailJob
{   
    private User user;
    
    private String site;
    
    public VerifyUserMessage()
    {
        super();
    }

    public VerifyUserMessage(User user, String site)
    {
        super();
        this.user = user;
        this.site = site;
    }
    
    public Message message(Session session) throws Exception
    {
        // build the message
        Message message = new MimeMessage(session);
        // from
        // TODO
        message.setFrom(new InternetAddress("discuss@bergamot-monitoring.org", "Bergamot Discuss"));
        // to address
        message.addRecipient(RecipientType.TO, new InternetAddress(user.getEmail(), user.getDisplayName()));
        // content
        message.setSubject("Welcome " + user.getDisplayName() + " to Bergamot Discuss");
        message.setContent(this.buildContent(), "text/plain");
        // done
        return message;
    }
    
    private String buildContent()
    {
        StringBuilder content = new StringBuilder();
        content.append("Thank you for registering with Bergamot Discuss.\r\n\r\n");
        content.append("Once you have followed these instructions to verify your account ");
        content.append("you will be able to post to the following discussions:\r\n\r\n");
        for (Discussion discussion : user.getJoinedDiscussions())
        {
            content.append(" * ").append(discussion.getSummary()).append(" - ").append(discussion.getEmail()).append("\r\n");
        }
        content.append("\r\n");
        content.append("To verify your account please open the following link:\r\n");
        content.append(this.site).append("?u=").append(this.user.getId()).append("&t=").append(this.user.getVerificationToken()).append("\r\n");
        return content.toString();
    }
}

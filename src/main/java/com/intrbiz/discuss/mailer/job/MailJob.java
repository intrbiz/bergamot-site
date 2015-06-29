package com.intrbiz.discuss.mailer.job;

import javax.mail.Message;
import javax.mail.Session;

public abstract class MailJob
{
    public boolean multiMessage()
    {
        return false;
    }
    
    public void begin()
    {
    }
    
    public abstract Message message(Session session) throws Exception;
    
    public void end()
    {
        
    }
}

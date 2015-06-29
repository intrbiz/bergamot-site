package com.intrbiz.discuss.mailer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import org.apache.log4j.Logger;

import com.intrbiz.discuss.mailer.job.MailJob;

public class DiscussMailSender
{
    private Logger logger = Logger.getLogger(DiscussMailSender.class);
    
    private Properties properties;
    
    private Session session;
    
    private String host;
    
    private String username;
    
    private String password;
    
    private Thread runner;
    
    private volatile boolean run = false;
    
    private BlockingQueue<MailJob> queue = new LinkedBlockingQueue<MailJob>();
    
    public DiscussMailSender(String host, String username, String password)
    {
        super();
        this.host = host;
        this.username = username;
        this.password = password;
        // properties
        this.properties = new Properties();
        this.properties.put("mail.smtp.port", 587);
        this.properties.put("mail.smtp.starttls.enable", true);
        this.properties.put("mail.smtp.auth", true);
        this.session = Session.getDefaultInstance(properties);
    }
    
    public void start()
    {
        synchronized (this)
        {
            if (this.runner == null)
            {
                this.run = true;
                this.runner = new Thread(new Runnable()
                {
                    public void run()
                    {
                        while (run)
                        {
                            try
                            {
                                // process the send queue
                                MailJob job = queue.poll(30, TimeUnit.SECONDS);
                                if (job != null) mail(job);
                            }
                            catch (Exception e)
                            {
                            }
                        }
                    }
                });
                this.runner.start();
            }
        }
    }
    
    public void queue(MailJob job)
    {
        try
        {
            this.queue.put(job);
        }
        catch (InterruptedException e)
        {
        }
    }
    
    public void mail(MailJob job)
    {
        try
        {
            // start the job
            job.begin();
            try
            {
                // send the message
                Transport transport = null;
                try
                {
                    transport = session.getTransport("smtp");
                    // connect
                    transport.connect(this.host, this.username, this.password);
                    // send
                    if (job.multiMessage())
                    {
                        Message message;
                        while ((message = job.message(session)) != null)
                        {
                            logger.debug("Sending message to: " + Arrays.asList(message.getAllRecipients()));
                            transport.sendMessage(message, message.getAllRecipients());
                        }
                    }
                    else
                    {
                        Message message = job.message(session);
                        if (message != null)
                        {
                            logger.debug("Sending message to: " + Arrays.asList(message.getAllRecipients()));
                            transport.sendMessage(message, message.getAllRecipients());
                        }
                    }
                }
                finally
                {
                    if (transport != null) transport.close();
                }
            }
            finally
            {
                job.end();
            }
        }
        catch (Throwable e)
        {
            logger.error("Failed to send email", e);
        }
    }
}

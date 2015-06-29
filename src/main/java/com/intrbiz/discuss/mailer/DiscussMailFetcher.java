package com.intrbiz.discuss.mailer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.intrbiz.Util;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.mailer.job.BroadcastMessage;
import com.intrbiz.discuss.model.DiscussPost;
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.FetchedEmail;
import com.intrbiz.discuss.model.User;

public class DiscussMailFetcher
{
    private Logger logger = Logger.getLogger(DiscussMailFetcher.class);
    
    private Properties properties;
    
    private Session session;
    
    private String host;
    
    private String username;
    
    private String password;
    
    private Thread runner;
    
    private volatile boolean run = false;
    
    public DiscussMailFetcher(String host, String username, String password)
    {
        super();
        this.host = host;
        this.username = username;
        this.password = password;
        // properties
        this.properties = new Properties();
        this.properties.put("mail.store.protocol", "pop3");
        this.properties.put("mail.pop3.port", "995");
        this.properties.put("mail.pop3.starttls.enable", "true");
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
                                fetch();
                                Thread.sleep(30_000L);
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
    
    public void fetch()
    {
        try
        {
            Store store = this.session.getStore("pop3s");
            try
            {
                // connect
                store.connect(this.host, this.username, this.password);
                // open inbox
                Folder emailFolder = store.getFolder("Inbox");
                emailFolder.open(Folder.READ_ONLY);
                // get messages
                try
                {
                    Message[] messages = emailFolder.getMessages();
                    for (Message message : messages)
                    {
                        this.processMessage(message);
                    }
                }
                finally
                {
                    emailFolder.close(false);
                }
            }
            finally
            {
                store.close();
            }
        }
        catch (Exception e)
        {
            logger.warn("Error fetching messages", e);
        }
    }
    
    private void processMessage(Message message) throws Exception
    {
        logger.trace("Message: " + message.getClass());
        logger.trace("From: " + Arrays.asList(message.getFrom()));
        logger.trace("To: " + Arrays.asList(message.getRecipients(RecipientType.TO)));
        logger.trace("Subject: " + message.getSubject());
        logger.trace("Type: " + message.getContentType());
        logger.trace("ID: " + message.getHeader("Message-ID"));
        logger.trace("IPT: " + message.getHeader("In-Reply-To"));
        logger.trace("--------");
        logger.trace(rawMessage(message));
        logger.trace("--------");
        // basic validation
        if (message.getFrom() == null || message.getFrom().length == 0)
        {
            logger.warn("Ignoring message with no from addresses");
            return;
        }
        // process the message
        try (DiscussDB db = DiscussDB.connect())
        {
            for (InternetAddress to : Arrays.asList(message.getAllRecipients()).stream().filter((a) -> a instanceof InternetAddress).map((a) -> (InternetAddress) a).collect(Collectors.toList()))
            {
                logger.trace("Looking up address: " + to.getAddress());
                // is the to address a discussion
                Discussion discussion = db.getDiscussionByEmail(to.getAddress());
                if (discussion != null)
                {
                    logger.info("Got message for discussion: " + discussion.getName());
                    // lookup the user in this discussion
                    User fromUser = Arrays.asList(message.getFrom()).stream().filter((a) -> a instanceof InternetAddress).map((a) -> { logger.info("Lookup From: " + ((InternetAddress)a).getAddress()); return db.getUserByEmailInDiscussion(((InternetAddress)a).getAddress(), discussion.getId()); }).filter((u) -> u != null).findFirst().orElse(null);
                    if (fromUser == null)
                    {
                        logger.debug("Ignoring message from non-user of discussion " + discussion.getName());
                        return;
                    }
                    // look at the headers
                    String messageId = Util.coalesceEmpty(getHeader(message, "Message-ID"), UUID.randomUUID().toString());
                    String inReplyTo = getHeader(message, "In-Reply-To");
                    // get the message content
                    String subject = message.getSubject();
                    String content = this.getPlainTextMessage(message);
                    if (Util.isEmpty(subject) || Util.isEmpty(content))
                    {
                        logger.warn("Ignoring message due to invalid content");
                        // TODO: bounce
                        return;
                    }
                    // lookup the thread
                    DiscussThread thread = this.lookupThread(db, inReplyTo, discussion, fromUser, subject);
                    // stash the message
                    FetchedEmail email = new FetchedEmail();
                    email.setId(UUID.randomUUID());
                    email.setMessageId(messageId);
                    email.setDiscussionId(discussion.getId());
                    email.setFromUserId(fromUser.getId());
                    email.setInReplyTo(inReplyTo);
                    email.setSubject(subject);
                    email.setReceived(new Timestamp(System.currentTimeMillis()));
                    email.setRawEmail(this.rawMessage(message));
                    email.setThreadId(thread.getThreadId());
                    db.setFetchedEmail(email);
                    // add the post
                    DiscussPost post = new DiscussPost();
                    post.setDiscussionId(discussion.getId());
                    post.setThreadId(thread.getThreadId());
                    post.setPostId(UUID.randomUUID());
                    post.setCreated(email.getReceived());
                    post.setUpdated(email.getReceived());
                    post.setSummary(message.getSubject());
                    post.setContent(content);
                    post.setFromUserId(fromUser.getId());
                    db.setPost(post);
                    // broadcast the message
                    DiscussMailSender sender = DiscussMailer.get().getSender();
                    if (sender != null) sender.queue(new BroadcastMessage(discussion, thread, fromUser, messageId, inReplyTo, subject, content));
                }
            }
        }
    }
    
    private DiscussThread lookupThread(DiscussDB db, String inReplyTo, Discussion discussion, User fromUser, String subject)
    {
        // is it in reply to our ids?
        if (inReplyTo != null && inReplyTo.endsWith("@" + discussion.getId() + ">"))
        {
            String[] ids = inReplyTo.substring(1, inReplyTo.length() - ("@" + discussion.getId() + ">").length()).split("\\.");
            if (ids.length > 1)
            {
                DiscussThread thread = db.getThread(discussion.getId(), UUID.fromString(ids[1]));
                if (thread != null)
                {
                    thread.setUpdated(new Timestamp(System.currentTimeMillis()));
                    thread.setPosts(thread.getPosts() + 1);
                    db.setThread(thread);
                    return thread;
                }    
            }
        }
        // replyTo fetched email
        FetchedEmail replyTo = Util.isEmpty(inReplyTo) ? null : db.getFetchedEmailByMessageIdInDiscussion(inReplyTo, discussion.getId());   
        if (replyTo != null)
        {
            DiscussThread thread = db.getThread(discussion.getId(), replyTo.getThreadId());
            if (thread != null)
            {
                thread.setUpdated(new Timestamp(System.currentTimeMillis()));
                thread.setPosts(thread.getPosts() + 1);
                db.setThread(thread);
                return thread;
            }
        }
        // create a new thread
        DiscussThread thread = new DiscussThread();
        thread.setDiscussionId(discussion.getId());
        thread.setThreadId(UUID.randomUUID());
        thread.setCreated(new Timestamp(System.currentTimeMillis()));
        thread.setUpdated(new Timestamp(System.currentTimeMillis()));
        thread.setPosts(1);
        thread.setSummary(subject);
        thread.setFromUserId(fromUser.getId());
        db.setThread(thread);
        return thread;
    }
    
    private String getHeader(Message message, String name) throws MessagingException
    {
        String[] values = message.getHeader(name);
        if (values == null || values.length == 0) return null;
        return values[0];
    }
    
    private String getPlainTextMessage(Part part) throws MessagingException, IOException
    {
        if (part.isMimeType("text/plain"))
        {
            return (String) part.getContent();
        }
        else if (part.isMimeType("multipart/*"))
        {
            Multipart multi = (Multipart) part;
            for (int i = 0 ; i < multi.getCount(); i++)
            {
                Part subPart = multi.getBodyPart(i);
                String text = getPlainTextMessage(subPart);
                if (text != null) return text;
            }
        }
        return null;
    }

    
    private String rawMessage(Message message) throws Exception
    {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream())
        {
            message.writeTo(out);
            return new String(out.toByteArray());
        }
    }
    
}

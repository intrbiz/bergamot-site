package com.intrbiz.discuss.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.intrbiz.data.db.compiler.meta.SQLColumn;
import com.intrbiz.data.db.compiler.meta.SQLPrimaryKey;
import com.intrbiz.data.db.compiler.meta.SQLTable;
import com.intrbiz.data.db.compiler.meta.SQLUnique;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.data.DiscussDB;

@SQLTable(schema = DiscussDB.class, name = "fetched_email", since = @SQLVersion({ 1, 0, 0 }))
@SQLUnique(name = "message_id_unq", columns = { "message_id" })
public class FetchedEmail
{   
    @SQLPrimaryKey()
    @SQLColumn(index = 1, name = "id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID id;
    
    @SQLColumn(index = 2, name = "message_id", since = @SQLVersion({ 1, 0, 0 }))
    private String messageId;
    
    @SQLColumn(index = 3, name = "discussion_id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID discussionId;
    
    @SQLColumn(index = 4, name = "from_user_id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID fromUserId;
    
    @SQLColumn(index = 5, name = "in_reply_to", since = @SQLVersion({ 1, 0, 0 }))
    private String inReplyTo;
    
    @SQLColumn(index = 6, name = "subject", since = @SQLVersion({ 1, 0, 0 }))
    private String subject;
    
    @SQLColumn(index = 7, name = "received", since = @SQLVersion({ 1, 0, 0 }))
    private Timestamp received;
    
    @SQLColumn(index = 8, name = "raw_email", since = @SQLVersion({ 1, 1, 0 }))
    private String rawEmail;
    
    @SQLColumn(index = 9, name = "thread_id", since = @SQLVersion({ 1, 2, 0 }))
    private UUID threadId;
    
    public FetchedEmail()
    {
        super();
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getMessageId()
    {
        return messageId;
    }

    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    public UUID getFromUserId()
    {
        return fromUserId;
    }

    public void setFromUserId(UUID fromUserId)
    {
        this.fromUserId = fromUserId;
    }

    public String getInReplyTo()
    {
        return inReplyTo;
    }

    public void setInReplyTo(String inReplyTo)
    {
        this.inReplyTo = inReplyTo;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public UUID getDiscussionId()
    {
        return discussionId;
    }

    public void setDiscussionId(UUID discussionId)
    {
        this.discussionId = discussionId;
    }

    public Timestamp getReceived()
    {
        return received;
    }

    public void setReceived(Timestamp received)
    {
        this.received = received;
    }

    public String getRawEmail()
    {
        return rawEmail;
    }

    public void setRawEmail(String rawEmail)
    {
        this.rawEmail = rawEmail;
    }

    public UUID getThreadId()
    {
        return threadId;
    }

    public void setThreadId(UUID threadId)
    {
        this.threadId = threadId;
    }
}

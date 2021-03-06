package com.intrbiz.discuss.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.intrbiz.data.db.compiler.meta.SQLColumn;
import com.intrbiz.data.db.compiler.meta.SQLPrimaryKey;
import com.intrbiz.data.db.compiler.meta.SQLTable;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.data.DiscussDB;

@SQLTable(schema = DiscussDB.class, name = "post", since = @SQLVersion({ 1, 2, 0 }))
public class DiscussPost
{    
    @SQLPrimaryKey()
    @SQLColumn(index = 1, name = "discussion_id", since = @SQLVersion({ 1, 2, 0 }))
    private UUID discussionId;
    
    @SQLPrimaryKey()
    @SQLColumn(index = 2, name = "post_id", since = @SQLVersion({ 1, 2, 0 }))
    private UUID postId;
    
    @SQLColumn(index = 3, name = "thread_id", since = @SQLVersion({ 1, 2, 0 }))
    private UUID threadId;
    
    @SQLColumn(index = 4, name = "from_user_id", since = @SQLVersion({ 1, 2, 0 }))
    private UUID fromUserId;
    
    @SQLColumn(index = 5, name = "summary", since = @SQLVersion({ 1, 2, 0 }))
    private String summary;
    
    @SQLColumn(index = 6, name = "description", since = @SQLVersion({ 1, 2, 0 }))
    private String description;
    
    @SQLColumn(index = 7, name = "content", since = @SQLVersion({ 1, 2, 0 }))
    private String content;

    @SQLColumn(index = 8, name = "created", since = @SQLVersion({ 1, 2, 0 }))
    private Timestamp created;
    
    @SQLColumn(index = 9, name = "updated", since = @SQLVersion({ 1, 2, 0 }))
    private Timestamp updated;
    
    public DiscussPost()
    {
        super();
    }

    public UUID getDiscussionId()
    {
        return discussionId;
    }

    public void setDiscussionId(UUID discussionId)
    {
        this.discussionId = discussionId;
    }

    public UUID getThreadId()
    {
        return threadId;
    }

    public void setThreadId(UUID threadId)
    {
        this.threadId = threadId;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Timestamp getCreated()
    {
        return created;
    }

    public void setCreated(Timestamp created)
    {
        this.created = created;
    }

    public Timestamp getUpdated()
    {
        return updated;
    }

    public void setUpdated(Timestamp updated)
    {
        this.updated = updated;
    }

    public UUID getPostId()
    {
        return postId;
    }

    public void setPostId(UUID postId)
    {
        this.postId = postId;
    }

    public UUID getFromUserId()
    {
        return fromUserId;
    }

    public void setFromUserId(UUID fromUserId)
    {
        this.fromUserId = fromUserId;
    }
    
    public User getFromUser()
    {
        try (DiscussDB db = DiscussDB.connect())
        {
            return db.getUser(this.getFromUserId());
        }
    }
}

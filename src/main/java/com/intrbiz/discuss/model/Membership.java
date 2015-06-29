package com.intrbiz.discuss.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.intrbiz.data.db.compiler.meta.SQLColumn;
import com.intrbiz.data.db.compiler.meta.SQLPrimaryKey;
import com.intrbiz.data.db.compiler.meta.SQLTable;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.data.DiscussDB;

@SQLTable(schema = DiscussDB.class, name = "membership", since = @SQLVersion({ 1, 0, 0 }))
public class Membership
{    
    @SQLPrimaryKey()
    @SQLColumn(index = 1, name = "discussion_id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID discussionId;
    
    @SQLPrimaryKey()
    @SQLColumn(index = 2, name = "user_id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID userId;

    @SQLColumn(index = 3, name = "joined", since = @SQLVersion({ 1, 0, 0 }))
    private Timestamp joined;
    
    public Membership()
    {
        super();
    }
    
    public Membership(UUID discussionId, UUID userId)
    {
        super();
        this.discussionId = discussionId;
        this.userId = userId;
        this.joined = new Timestamp(System.currentTimeMillis());
    }

    public UUID getDiscussionId()
    {
        return discussionId;
    }

    public void setDiscussionId(UUID discussionId)
    {
        this.discussionId = discussionId;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public Timestamp getJoined()
    {
        return joined;
    }

    public void setJoined(Timestamp joined)
    {
        this.joined = joined;
    }
}

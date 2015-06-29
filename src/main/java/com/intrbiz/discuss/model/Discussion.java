package com.intrbiz.discuss.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.intrbiz.data.db.compiler.meta.SQLColumn;
import com.intrbiz.data.db.compiler.meta.SQLPrimaryKey;
import com.intrbiz.data.db.compiler.meta.SQLTable;
import com.intrbiz.data.db.compiler.meta.SQLUnique;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.data.DiscussDB;

@SQLTable(schema = DiscussDB.class, name = "discussion", since = @SQLVersion({ 1, 0, 0 }))
@SQLUnique(name = "email_unq", columns = { "email" })
public class Discussion
{    
    @SQLPrimaryKey()
    @SQLColumn(index = 1, name = "id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID id;
    
    @SQLColumn(index = 2, name = "email", since = @SQLVersion({ 1, 0, 0 }))
    private String email;
    
    @SQLColumn(index = 3, name = "name", since = @SQLVersion({ 1, 0, 0 }))
    private String name;
    
    @SQLColumn(index = 4, name = "summary", since = @SQLVersion({ 1, 0, 0 }))
    private String summary;
    
    @SQLColumn(index = 5, name = "description", since = @SQLVersion({ 1, 0, 0 }))
    private String description;
    
    @SQLColumn(index = 6, name = "content", since = @SQLVersion({ 1, 0, 0 }))
    private String content;

    @SQLColumn(index = 7, name = "created", since = @SQLVersion({ 1, 0, 0 }))
    private Timestamp created;
    
    public Discussion()
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }
    
    public boolean isMember(User user)
    {
        if (user == null) return false;
        try (DiscussDB db = DiscussDB.connect())
        {
            return db.getMembership(this.getId(), user.getId()) != null;
        }
    }
}

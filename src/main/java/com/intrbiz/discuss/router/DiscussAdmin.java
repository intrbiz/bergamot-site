package com.intrbiz.discuss.router;

import java.sql.Timestamp;
import java.util.UUID;

import com.intrbiz.balsa.BalsaApplication;
import com.intrbiz.balsa.engine.route.Router;
import com.intrbiz.balsa.error.http.BalsaNotFound;
import com.intrbiz.balsa.metadata.WithDataAdapter;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.metadata.Any;
import com.intrbiz.metadata.Get;
import com.intrbiz.metadata.IsaUUID;
import com.intrbiz.metadata.Param;
import com.intrbiz.metadata.Post;
import com.intrbiz.metadata.Prefix;
import com.intrbiz.metadata.RequirePermission;
import com.intrbiz.metadata.RequireValidPrincipal;
import com.intrbiz.metadata.Template;

@Prefix("/discuss/admin")
@Template("layout/main")
@RequireValidPrincipal()
@RequirePermission("discuss.admin")
public class DiscussAdmin extends Router<BalsaApplication>
{
    public DiscussAdmin()
    {
        super();
    }
    
    
    @Any("/")
    @WithDataAdapter(DiscussDB.class)
    public void index(DiscussDB db)
    {
        var("discussions", db.getDiscussions());
        encode("discuss/admin/index");
    }
    
    @Get("/discussion/create")
    public void createDiscussion()
    {
        encode("discuss/admin/discussion/create");
    }
    
    @Post("/discussion/create")
    @WithDataAdapter(DiscussDB.class)
    public void doCreateDiscussion(
        DiscussDB db,
        @Param("name") String name,
        @Param("email") String email,
        @Param("summary") String summary,
        @Param("description") String description,
        @Param("content") String content
    ) throws Exception
    {
        Discussion disc = new Discussion();
        disc.setId(UUID.randomUUID());
        disc.setName(name);
        disc.setEmail(email);
        disc.setSummary(summary);
        disc.setDescription(description);
        disc.setContent(content);
        disc.setCreated(new Timestamp(System.currentTimeMillis()));
        db.setDiscussion(disc);
        redirect(path("discuss/admin/"));
    }
    
    @Get("/discussion/:id/")
    @WithDataAdapter(DiscussDB.class)
    public void discussionDetails(DiscussDB db, @IsaUUID UUID discussionId)
    {
        Discussion disc = var("discussion", db.getDiscussion(discussionId));
        if (disc == null) throw new BalsaNotFound("No such discussion");
        var("users", db.getUsersInDiscussion(discussionId));
        encode("discuss/admin/discussion/details");
    }
    
    @Post("/discussion/:id/update")
    @WithDataAdapter(DiscussDB.class)
    public void doCreateDiscussion(
        DiscussDB db,
        @IsaUUID UUID discussionId,
        @Param("name") String name,
        @Param("email") String email,
        @Param("summary") String summary,
        @Param("description") String description,
        @Param("content") String content
    ) throws Exception
    {
        Discussion disc = db.getDiscussion(discussionId);
        if (disc == null) throw new BalsaNotFound("No such discussion");
        disc.setName(name);
        disc.setEmail(email);
        disc.setSummary(summary);
        disc.setDescription(description);
        disc.setContent(content);
        disc.setCreated(new Timestamp(System.currentTimeMillis()));
        db.setDiscussion(disc);
        redirect(path("discuss/admin/discussion/" + discussionId + "/"));
    }
}

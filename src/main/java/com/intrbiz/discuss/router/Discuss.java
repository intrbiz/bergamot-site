package com.intrbiz.discuss.router;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.intrbiz.balsa.BalsaApplication;
import com.intrbiz.balsa.engine.route.Router;
import com.intrbiz.balsa.error.http.BalsaNotFound;
import com.intrbiz.balsa.metadata.WithDataAdapter;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.mailer.DiscussMailer;
import com.intrbiz.discuss.mailer.job.CreatedPostMessage;
import com.intrbiz.discuss.mailer.job.CreatedThreadMessage;
import com.intrbiz.discuss.mailer.job.NewUserAdminMessage;
import com.intrbiz.discuss.mailer.job.VerifyUserMessage;
import com.intrbiz.discuss.model.DiscussPost;
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.Membership;
import com.intrbiz.discuss.model.User;
import com.intrbiz.metadata.Any;
import com.intrbiz.metadata.Get;
import com.intrbiz.metadata.IsaUUID;
import com.intrbiz.metadata.ListParam;
import com.intrbiz.metadata.Param;
import com.intrbiz.metadata.Post;
import com.intrbiz.metadata.Prefix;
import com.intrbiz.metadata.RequireValidPrincipal;
import com.intrbiz.metadata.Template;

@Prefix("/discuss/")
@Template("layout/main")
public class Discuss extends Router<BalsaApplication>
{
    public Discuss()
    {
        super();
    }
    
    @Get("/")
    @WithDataAdapter(DiscussDB.class)
    public void index(DiscussDB db)
    {
        var("discussions", db.getDiscussions());
        encode("discuss/index");
    }
    
    @Get("/discussion/:id/")
    @WithDataAdapter(DiscussDB.class)
    public void discussionDetail(DiscussDB db, @IsaUUID UUID discussionId)
    {
        Discussion disc = var("discussion", db.getDiscussion(discussionId));
        if (disc == null) throw new BalsaNotFound();
        var("threads", db.getThreadsInDiscussion(discussionId));
        var("isMember", disc.isMember(currentPrincipal()));
        encode("discuss/discussion/detail");
    }
    
    @Get("/discussion/:did/thread/:tid/")
    @WithDataAdapter(DiscussDB.class)
    public void threadDetail(DiscussDB db, @IsaUUID UUID discussionId, @IsaUUID UUID threadId)
    {
        Discussion disc = var("discussion", db.getDiscussion(discussionId));
        if (disc == null) throw new BalsaNotFound();
        DiscussThread thread = var("thread", db.getThread(discussionId, threadId));
        if (thread == null) throw new BalsaNotFound();
        var("posts", db.getPostsInThread(discussionId, threadId));
        var("isMember", disc.isMember(currentPrincipal()));
        encode("discuss/thread/detail");
    }
    
    @Post("/discussion/:did/thread/create")
    @RequireValidPrincipal()
    @WithDataAdapter(DiscussDB.class)
    public void threadCreate(
            DiscussDB db, 
            @IsaUUID UUID discussionId,
            @Param("summary") String summary,
            @Param("description") String description
    ) throws Exception
    {
        // the user
        User user = currentPrincipal();
        // get the discussion
        Discussion disc = var("discussion", db.getDiscussion(discussionId));
        if (disc == null) throw new BalsaNotFound();
        // check membership
        require(disc.isMember(user));
        // create the thread
        DiscussThread thread = new DiscussThread();
        thread.setDiscussionId(disc.getId());
        thread.setThreadId(UUID.randomUUID());
        thread.setFromUserId(user.getId());
        thread.setSummary(summary);
        thread.setDescription(description);
        thread.setCreated(new Timestamp(System.currentTimeMillis()));
        thread.setUpdated(thread.getCreated());
        thread.setPosts(0);
        db.setThread(thread);
        // mail
        DiscussMailer.get().getSender().queue(new CreatedThreadMessage(disc, thread));
        // show
        redirect(path("discuss/discussion/" + discussionId + "/thread/" + thread.getThreadId() + "/"));
    }
    
    @Post("/discussion/:did/thread/:tid/post")
    @RequireValidPrincipal()
    @WithDataAdapter(DiscussDB.class)
    public void threadPost(
            DiscussDB db, 
            @IsaUUID UUID discussionId,
            @IsaUUID UUID threadId,
            @Param("subject") String subject,
            @Param("message") String message
    ) throws Exception
    {
        // the user
        User user = currentPrincipal();
        // get the discussion
        Discussion disc = var("discussion", db.getDiscussion(discussionId));
        if (disc == null) throw new BalsaNotFound();
        // check membership
        require(disc.isMember(user));
        // get the thread
        DiscussThread thread = var("thread", db.getThread(discussionId, threadId));
        if (thread == null) throw new BalsaNotFound();
        // create the post
        DiscussPost post = new DiscussPost();
        post.setDiscussionId(disc.getId());
        post.setThreadId(thread.getThreadId());
        post.setPostId(UUID.randomUUID());
        post.setFromUserId(user.getId());
        post.setSummary(subject);
        post.setContent(message);
        post.setCreated(new Timestamp(System.currentTimeMillis()));
        post.setUpdated(thread.getCreated());
        db.setPost(post);
        // mail
        DiscussMailer.get().getSender().queue(new CreatedPostMessage(disc, thread, post));
        // show
        redirect(path("discuss/discussion/" + discussionId + "/thread/" + thread.getThreadId() + "/"));
    }
    
    @Get("/register")
    @WithDataAdapter(DiscussDB.class)
    public void register(DiscussDB db)
    {
        var("discussions", db.getDiscussions());
        encode("discuss/register");
    }
    
    @Post("/register")
    @WithDataAdapter(DiscussDB.class)
    public void doRegister(
            DiscussDB db,
            @Param("display_name") String displayName,
            @Param("email") String email,
            @Param("password") String password,
            @Param("password_confirm") String passwordConfirm,
            @ListParam("join") @IsaUUID List<UUID> joins
    )
    {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setDisplayName(displayName);
        user.setEmail(email);
        user.hashPassword(password);
        user.setLocked(false);
        user.setForcePasswordChange(true);
        user.setRole("user");
        user.setVerified(false);
        user.setVerifiedAt(null);
        user.setVerificationToken(UUID.randomUUID() + "-" + UUID.randomUUID());
        user.setRegistered(new Timestamp(System.currentTimeMillis()));
        db.setUser(user);
        // memberships
        for (UUID join : joins)
        {
            db.setMembership(new Membership(join, user.getId()));
        }
        // send verification email
        DiscussMailer.get().getSender().queue(new VerifyUserMessage(user, url("discuss/verify")));
        DiscussMailer.get().getSender().queue(new NewUserAdminMessage(user));
        // done
        var("user", user);
        var("discussions", db.getDiscussionsByUser(user.getId()));
        encode("discuss/registered");
    }
    
    @Get("/profile")
    @RequireValidPrincipal()
    @WithDataAdapter(DiscussDB.class)
    public void profile(DiscussDB db)
    {
        var("user", currentPrincipal());
        var("discussions", db.getDiscussions());
        var("joined", db.getDiscussionsByUser(((User) currentPrincipal()).getId()));
        encode("discuss/profile");
    }
    
    @Any("/verify")
    @WithDataAdapter(DiscussDB.class)
    public void verify(DiscussDB db, @Param("u") @IsaUUID UUID userId, @Param("t") String token)
    {
        User user = var("user", db.getUser(userId));
        if (user == null) throw new BalsaNotFound();
        // verify
        if (user.getVerificationToken().equals(token))
        {
            user.setVerified(true);
            user.setLocked(false);
            user.setVerifiedAt(new Timestamp(System.currentTimeMillis()));
            db.setUser(user);
            // force authentication
            // TODO: balsa().session().currentPrincipal(user);
        }
        else
        {
            // TODO: better handling
            throw new BalsaNotFound();
        }
        var("discussions", db.getDiscussionsByUser(user.getId()));
        encode("discuss/verified");
    }
    
    @Post("/join")
    @RequireValidPrincipal()
    @WithDataAdapter(DiscussDB.class)
    public void join(
            DiscussDB db,
            @ListParam("join") @IsaUUID List<UUID> joins
    )
    {
        User user = currentPrincipal();
        // memberships
        for (UUID join : joins)
        {
            db.setMembership(new Membership(join, user.getId()));
        }
        // done
        var("user", user);
        var("discussions", db.getDiscussions());
        var("joined", db.getDiscussionsByUser(user.getId()));
        encode("discuss/profile");
    }
    
    @Get("/leave/:id")
    @RequireValidPrincipal()
    @WithDataAdapter(DiscussDB.class)
    public void leave(
            DiscussDB db,
            @IsaUUID UUID discussionId
    )
    {
        User user = currentPrincipal();
        // remove membership
        db.removeMembership(discussionId, user.getId());
        // done
        var("user", user);
        var("discussions", db.getDiscussions());
        var("joined", db.getDiscussionsByUser(user.getId()));
        encode("discuss/profile");
    }
    
    @Post("/changepassword")
    @WithDataAdapter(DiscussDB.class)
    public void doChangePassword(
            DiscussDB db,
            @Param("password") String password,
            @Param("password_confirm") String passwordConfirm
    )
    {
        User user = currentPrincipal();
        // change
        if (password.equals(passwordConfirm))
        {
            user.hashPassword(password);
            db.setUser(user);
            var("password_changed", true);
        }
        else
        {
            var("password_change_error", "mismatch");
        }
        // done
        var("user", user);
        var("discussions", db.getDiscussions());
        var("joined", db.getDiscussionsByUser(user.getId()));
        encode("discuss/profile");
    }
}

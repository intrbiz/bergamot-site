package com.intrbiz.discuss.data;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.intrbiz.data.DataManager;
import com.intrbiz.data.cache.Cache;
import com.intrbiz.data.db.DatabaseAdapter;
import com.intrbiz.data.db.DatabaseConnection;
import com.intrbiz.data.db.compiler.DatabaseAdapterCompiler;
import com.intrbiz.data.db.compiler.meta.Direction;
import com.intrbiz.data.db.compiler.meta.SQLGetter;
import com.intrbiz.data.db.compiler.meta.SQLOrder;
import com.intrbiz.data.db.compiler.meta.SQLParam;
import com.intrbiz.data.db.compiler.meta.SQLQuery;
import com.intrbiz.data.db.compiler.meta.SQLRemove;
import com.intrbiz.data.db.compiler.meta.SQLSchema;
import com.intrbiz.data.db.compiler.meta.SQLSetter;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.model.DiscussPost;
import com.intrbiz.discuss.model.DiscussThread;
import com.intrbiz.discuss.model.Discussion;
import com.intrbiz.discuss.model.FetchedEmail;
import com.intrbiz.discuss.model.Membership;
import com.intrbiz.discuss.model.User;

@SQLSchema(
        name = "discuss", 
        version = @SQLVersion({1, 5, 0}),
        tables = {
            User.class,
            Discussion.class,
            Membership.class,
            FetchedEmail.class,
            DiscussThread.class,
            DiscussPost.class
        }
)
public abstract class DiscussDB extends DatabaseAdapter
{
    static
    {
        DataManager.getInstance().registerDatabaseAdapter(
                DiscussDB.class, 
                DatabaseAdapterCompiler.defaultPGSQLCompiler().compileAdapterFactory(DiscussDB.class)
        );
    }
    
    public static void load()
    {
        // do nothing
    }
    
    public static void install()
    {
        Logger logger = Logger.getLogger(DiscussDB.class);
        DatabaseConnection database = DataManager.getInstance().connect();
        DatabaseAdapterCompiler compiler =  DatabaseAdapterCompiler.defaultPGSQLCompiler().setDefaultOwner("bergamot");
        // check if the schema is installed
        if (! compiler.isSchemaInstalled(database, DiscussDB.class))
        {
            logger.info("Installing database schema");
            compiler.installSchema(database, DiscussDB.class);
        }
        else
        {
            // check the installed schema is upto date
            if (! compiler.isSchemaUptoDate(database, DiscussDB.class))
            {
                logger.info("The installed database schema is not upto date");
                compiler.upgradeSchema(database, DiscussDB.class);
            }
            else
            {
                logger.info("The installed database schema is upto date");
            }
        }
    }

    public static DiscussDB connect()
    {
        return DataManager.getInstance().databaseAdapter(DiscussDB.class);
    }

    public static DiscussDB connect(DatabaseConnection connection)
    {
        return DataManager.getInstance().databaseAdapter(DiscussDB.class, connection);
    }

    public DiscussDB(DatabaseConnection connection, Cache cache)
    {
        super(connection, cache);
    }
    
    // User
    
    @SQLSetter(table = User.class, name = "set_user", since = @SQLVersion({1, 0, 0}))
    public abstract void setUser(User user);
    
    @SQLGetter(table = User.class, name = "list_users", since = @SQLVersion({1, 0, 0}))
    public abstract List<User> getUsers();
    
    @SQLGetter(table = User.class, name = "get_user", since = @SQLVersion({1, 0, 0}))
    public abstract User getUser(@SQLParam("id") UUID id);
    
    @SQLGetter(table = User.class, name = "get_user_by_email", since = @SQLVersion({1, 0, 0}))
    public abstract User getUserByEmail(@SQLParam("email") String email);
    
    @SQLGetter(table = User.class, name = "get_user_by_email_in_discussion", since = @SQLVersion({1, 1, 0}),
        query = @SQLQuery("SELECT u.* FROM discuss.user u JOIN discuss.membership m ON (m.user_id = u.id) WHERE m.discussion_id = p_discussion_id AND u.email = p_email")
    )
    public abstract User getUserByEmailInDiscussion(@SQLParam(value = "email", virtual = true) String email, @SQLParam(value = "discussion_id", virtual = true) UUID discussionId);
    
    @SQLGetter(table = User.class, name = "get_users_in_discussion", since = @SQLVersion({1, 0, 0}),
        query = @SQLQuery("SELECT u.* FROM discuss.user u JOIN discuss.membership m ON (m.user_id = u.id) WHERE m.discussion_id = p_discussion_id ORDER BY m.joined")
    )
    public abstract List<User> getUsersInDiscussion(@SQLParam(value="discussion_id", virtual=true) UUID discussionId);
    
    @SQLGetter(table = User.class, name = "get_active_users_in_discussion", since = @SQLVersion({1, 5, 0}),
            query = @SQLQuery("SELECT u.* FROM discuss.user u JOIN discuss.membership m ON (m.user_id = u.id) WHERE m.discussion_id = p_discussion_id AND u.locked = FALSE AND u.verified = TRUE ORDER BY m.joined")
        )
        public abstract List<User> getActiveUsersInDiscussion(@SQLParam(value="discussion_id", virtual=true) UUID discussionId);
    
    @SQLRemove(table = User.class, name = "remove_user", since = @SQLVersion({1, 0, 0}))
    public abstract void removeUser(@SQLParam("id") UUID id);
    
    // Discussions
    
    @SQLSetter(table = Discussion.class, name = "set_discussion", since = @SQLVersion({1, 0, 0}))
    public abstract void setDiscussion(Discussion discussion);
    
    @SQLGetter(table = Discussion.class, name = "list_discussions", since = @SQLVersion({1, 0, 0}))
    public abstract List<Discussion> getDiscussions();
    
    @SQLGetter(table = Discussion.class, name = "get_discussion", since = @SQLVersion({1, 0, 0}))
    public abstract Discussion getDiscussion(@SQLParam("id") UUID id);
    
    @SQLGetter(table = Discussion.class, name = "get_discussion_by_email", since = @SQLVersion({1, 0, 0}))
    public abstract Discussion getDiscussionByEmail(@SQLParam("email") String email);
    
    @SQLGetter(table = Discussion.class, name = "get_discussion_by_user", since = @SQLVersion({1, 4, 0}),
        query = @SQLQuery("SELECT d.* FROM discuss.discussion d JOIN discuss.membership m ON (m.discussion_id = d.id) WHERE m.user_id = p_user_id")
    )
    public abstract List<Discussion> getDiscussionsByUser(@SQLParam(value ="user_id", virtual = true) UUID userId);
    
    @SQLRemove(table = Discussion.class, name = "remove_discussion", since = @SQLVersion({1, 0, 0}))
    public abstract void removeDiscussion(@SQLParam("id") UUID id);
    
    // Membership
    
    @SQLSetter(table = Membership.class, name = "set_membership", since = @SQLVersion({1, 0, 0}))
    public abstract void setMembership(Membership membership);
    
    @SQLGetter(table = Membership.class, name = "list_memberships", since = @SQLVersion({1, 0, 0}))
    public abstract List<Membership> getMemberships();
    
    @SQLGetter(table = Membership.class, name = "get_membership", since = @SQLVersion({1, 0, 0}))
    public abstract Membership getMembership(@SQLParam("discussion_id") UUID discussionId, @SQLParam("user_id") UUID userId);
    
    @SQLRemove(table = Membership.class, name = "remove_membership", since = @SQLVersion({1, 0, 0}))
    public abstract void removeMembership(@SQLParam("discussion_id") UUID discussionId, @SQLParam("user_id") UUID userId);
    
    // Fetched Emails
    
    @SQLSetter(table = FetchedEmail.class, name = "set_fetched_email", since = @SQLVersion({1, 1, 0}))
    public abstract void setFetchedEmail(FetchedEmail fetchedEmail);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_email", since = @SQLVersion({1, 1, 0}))
    public abstract FetchedEmail getFetchedEmail(@SQLParam("id") UUID id);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_email_by_message_id", since = @SQLVersion({1, 2, 0}))
    public abstract FetchedEmail getFetchedEmailByMessageId(@SQLParam("message_id") String messageId);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_email_by_message_id_in_discussion", since = @SQLVersion({1, 2, 0}))
    public abstract FetchedEmail getFetchedEmailByMessageIdInDiscussion(@SQLParam("message_id") String messageId, @SQLParam("discussion_id") UUID discussionId);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_emails_by_from_user", since = @SQLVersion({1, 1, 0}))
    public abstract List<FetchedEmail> getFetchedEmailsByFromUser(@SQLParam("from_user_id") UUID fromUserId);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_emails_by_discussion", since = @SQLVersion({1, 1, 0}))
    public abstract List<FetchedEmail> getFetchedEmailsByDiscussion(@SQLParam("discussion_id") UUID discussionId);
    
    @SQLGetter(table = FetchedEmail.class, name = "get_fetched_emails_by_discussion_thread", since = @SQLVersion({1, 2, 0}))
    public abstract List<FetchedEmail> getFetchedEmailsByDiscussionThread(@SQLParam("discussion_id") UUID discussionId, @SQLParam("thread_id") UUID threadId);
    
    @SQLRemove(table = FetchedEmail.class, name = "remove_fetched_email", since = @SQLVersion({1, 1, 0}))
    public abstract void removeFetchedEmail(@SQLParam("id") UUID id);
    
    // Threads
    
    @SQLSetter(table = DiscussThread.class, name = "set_thread", since = @SQLVersion({1, 2, 0}))
    public abstract void setThread(DiscussThread thread);
    
    @SQLGetter(table = DiscussThread.class, name = "get_thread", since = @SQLVersion({1, 2, 0}))
    public abstract DiscussThread getThread(@SQLParam("discussion_id") UUID discussionId, @SQLParam("thread_id") UUID threadId);
    
    @SQLGetter(table = DiscussThread.class, name = "get_threads_in_discussion", since = @SQLVersion({1, 2, 0}), orderBy = @SQLOrder(value = "updated", direction = Direction.DESC))
    public abstract List<DiscussThread> getThreadsInDiscussion(@SQLParam("discussion_id") UUID discussionId);
    
    @SQLRemove(table = DiscussThread.class, name = "remove_thread", since = @SQLVersion({1, 2, 0}))
    public abstract void removeThread(@SQLParam("discussion_id") UUID discussionId, @SQLParam("thread_id") UUID threadId);
    
    // Post
    
    @SQLSetter(table = DiscussPost.class, name = "set_post", since = @SQLVersion({1, 2, 0}))
    public abstract void setPost(DiscussPost discussPost);
    
    @SQLGetter(table = DiscussPost.class, name = "get_post", since = @SQLVersion({1, 2, 0}))
    public abstract DiscussPost getPost(@SQLParam("discussion_id") UUID discussionId, @SQLParam("post_id") UUID postId);
    
    @SQLGetter(table = DiscussPost.class, name = "get_posts_in_thread", since = @SQLVersion({1, 2, 0}), orderBy = @SQLOrder("created"))
    public abstract List<DiscussPost> getPostsInThread(@SQLParam("discussion_id") UUID discussionId, @SQLParam("thread_id") UUID threadId);
    
    @SQLRemove(table = DiscussPost.class, name = "remove_post", since = @SQLVersion({1, 2, 0}))
    public abstract void removePost(@SQLParam("discussion_id") UUID discussionId, @SQLParam("post_id") UUID postId);
    
    public static void main(String[] args) throws Exception
    {
        if (args.length == 1 && "install".equals(args[0]))
        {
            DatabaseAdapterCompiler.main(new String[] { "install", DiscussDB.class.getCanonicalName() });
        }
        else if (args.length == 2 && "upgrade".equals(args[0]))
        {
            DatabaseAdapterCompiler.main(new String[] { "upgrade", DiscussDB.class.getCanonicalName(), args[1] });
        }
        else
        {
            // interactive
            try (Scanner input = new Scanner(System.in))
            {
                for (;;)
                {
                    System.out.print("Would you like to generate the install or upgrade schema: ");
                    String action = input.nextLine();
                    // process the action
                    if ("exit".equals(action) || "quit".equals(action) || "q".equals(action))
                    {
                        System.exit(0);
                    }
                    else if ("install".equalsIgnoreCase(action) || "in".equalsIgnoreCase(action) || "i".equalsIgnoreCase(action))
                    {
                        DatabaseAdapterCompiler.main(new String[] { "install", DiscussDB.class.getCanonicalName() });
                        System.exit(0);
                    }
                    else if ("upgrade".equalsIgnoreCase(action) || "up".equalsIgnoreCase(action) || "u".equalsIgnoreCase(action))
                    {
                        System.out.print("What is the current installed version: ");
                        String version = input.nextLine();
                        DatabaseAdapterCompiler.main(new String[] { "upgrade", DiscussDB.class.getCanonicalName(), version });
                        System.exit(0);
                    }
                }
            }
        }
    }
}

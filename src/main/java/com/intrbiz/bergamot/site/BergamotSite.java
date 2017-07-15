package com.intrbiz.bergamot.site;

import com.intrbiz.balsa.BalsaApplication;
import com.intrbiz.balsa.BalsaMarkdown;
import com.intrbiz.bergamot.site.api.APIRouter;
import com.intrbiz.bergamot.site.router.AppRouter;
import com.intrbiz.data.DataManager;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.mailer.DiscussMailFetcher;
import com.intrbiz.discuss.mailer.DiscussMailSender;
import com.intrbiz.discuss.mailer.DiscussMailer;
import com.intrbiz.discuss.router.Discuss;
import com.intrbiz.discuss.router.DiscussAdmin;
import com.intrbiz.discuss.router.DiscussLogin;
import com.intrbiz.discuss.security.DiscussSecurityEngine;
import com.intrbiz.util.pool.database.DatabasePool;

public class BergamotSite extends BalsaApplication
{    
    @Override
    protected void setupEngines() throws Exception
    {
        // Setup Markdown support
        BalsaMarkdown.enable(this);
        // Security engine
        securityEngine(new DiscussSecurityEngine());
    }
    
    @Override
    protected void setupRouters() throws Exception
    {
        // Setup the application routers
        // discuss
        router(new Discuss());
        router(new DiscussLogin());
        router(new DiscussAdmin());
        // api
        router(new APIRouter());
        // main site
        router(new AppRouter());
    }
    
    @Override
    protected void setupFunctions() throws Exception
    {
    }

    @Override
    protected void setupActions() throws Exception
    {        
    }

    @Override
    protected void startApplication() throws Exception
    {        
    }

    public static void main(String[] args) throws Exception
    {
        // setup the database
        DataManager.getInstance().registerDefaultServer(
                DatabasePool.Default.with().postgresql()
                 .url("jdbc:postgresql://" + System.getProperty("discuss.db.host", "127.0.0.1") + "/" + System.getProperty("discuss.db.name", "bergamot_site"))
                 .username(System.getProperty("discuss.db.user", "bergamot"))
                 .password(System.getProperty("discuss.db.pass", "bergamot"))
                 .build()
        );
        // setup Discuss DB
        DiscussDB.install();
        // setup the discuss mailer
        DiscussMailer.get().configure(
                new DiscussMailFetcher(System.getProperty("discuss.pop.host"), System.getProperty("discuss.pop.user"), System.getProperty("discuss.pop.pass")), 
                new DiscussMailSender(System.getProperty("discuss.smtp.host"), System.getProperty("discuss.smtp.user"), System.getProperty("discuss.smtp.pass"))
        );
        DiscussMailer.get().start();
        // start the app
        BergamotSite bergamotSite = new BergamotSite();
        bergamotSite.start();
    }
}

package com.intrbiz.bergamot.site;

import com.intrbiz.balsa.BalsaApplication;
import com.intrbiz.balsa.BalsaMarkdown;
import com.intrbiz.bergamot.site.router.AppRouter;

public class BergamotSite extends BalsaApplication
{
    @Override
    protected void setup()
    {
        // Setup Markdown support
        BalsaMarkdown.enable(this);
        // Setup the application routers
        router(new AppRouter());
    }
    
    public static void main(String[] args) throws Exception
    {
        BergamotSite bergamotSite = new BergamotSite();
        bergamotSite.start();
    }
}

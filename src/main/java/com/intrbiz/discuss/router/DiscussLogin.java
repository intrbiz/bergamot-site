package com.intrbiz.discuss.router;

import java.io.IOException;

import com.intrbiz.balsa.BalsaApplication;
import com.intrbiz.balsa.engine.route.Router;
import com.intrbiz.balsa.error.BalsaSecurityException;
import com.intrbiz.balsa.metadata.WithDataAdapter;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.metadata.Any;
import com.intrbiz.metadata.Catch;
import com.intrbiz.metadata.Get;
import com.intrbiz.metadata.Order;
import com.intrbiz.metadata.Param;
import com.intrbiz.metadata.Post;
import com.intrbiz.metadata.Prefix;
import com.intrbiz.metadata.Template;

@Prefix("/discuss/")
@Template("layout/main")
public class DiscussLogin extends Router<BalsaApplication>
{
    public DiscussLogin()
    {
        super();
    }
    
    @Any("/logout")
    public void logout() throws IOException
    {
        deauthenticate();
        redirect(path("discuss/"));
    }
    
    @Get("/login")
    public void login()
    {
        encode("discuss/login");
    }
    
    @Post("/login")
    @WithDataAdapter(DiscussDB.class)
    public void doLogin(DiscussDB db, @Param("email") String email, @Param("password") String password) throws Exception
    {
        authenticate(email, password);
        // redirect to the admin interface
        redirect(path("discuss/"));
    }
    
    @Catch(BalsaSecurityException.class)
    @Order(Order.LAST - 10)
    @Any("/**")
    public void forceLogin() throws IOException
    {
        redirect(path("discuss/login"));
    }
}

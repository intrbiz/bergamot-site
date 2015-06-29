package com.intrbiz.discuss.security;

import java.security.Principal;

import org.apache.log4j.Logger;

import com.intrbiz.balsa.BalsaException;
import com.intrbiz.balsa.engine.impl.security.SecurityEngineImpl;
import com.intrbiz.balsa.error.BalsaSecurityException;
import com.intrbiz.data.DataException;
import com.intrbiz.discuss.data.DiscussDB;
import com.intrbiz.discuss.model.User;

public class DiscussSecurityEngine extends SecurityEngineImpl
{
    private Logger logger = Logger.getLogger(DiscussSecurityEngine.class);

    public DiscussSecurityEngine()
    {
        super();
    }

    @Override
    public String getEngineName()
    {
        return "Discuss Security Engine";
    }
    
    protected String getMetricsIntelligenceSourceName()
    {
        return "com.intrbiz.discuss";
    }

    @Override
    public void start() throws BalsaException
    {
    }

    @Override
    protected Principal doPasswordLogin(String username, char[] password) throws BalsaSecurityException
    {
        try (DiscussDB db = DiscussDB.connect())
        {
            logger.info("Authentication for principal: " + username);
            // lookup the principal
            User user = db.getUserByEmail(username);
            // does the username exist?
            if (user == null) return null;
            // check the password
            if (! user.verifyPassword(new String(password)))
            {
                logger.error("Password mismatch for principal " + username);
                throw new BalsaSecurityException("Invalid password");
            }
            // verify the account
            if (user.isLocked())
            {
                logger.error("Account is locked for principal " + username);
                throw new BalsaSecurityException("Account locked");
            }
            if (! user.isVerified())
            {
                logger.error("Account is not yet verified for principal " + username);
                throw new BalsaSecurityException("Account not verified");
            }
            return user;
        }
        catch (DataException e)
        {
            logger.error("Cannot authenticate principal, database error", e);
            throw new BalsaSecurityException("Error authenticating principal");
        }
    }

    @Override
    public boolean check(Principal principal, String permission)
    {
        if (principal instanceof User)
        {
            User user = (User) principal;
            // admins can do anything
            if ("admin".equals(user.getRole())) return true;
            // more detailed processing
        }
        return false;
    }
}

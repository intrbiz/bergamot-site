package com.intrbiz.discuss.model;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import com.intrbiz.data.db.compiler.meta.SQLColumn;
import com.intrbiz.data.db.compiler.meta.SQLPrimaryKey;
import com.intrbiz.data.db.compiler.meta.SQLTable;
import com.intrbiz.data.db.compiler.meta.SQLUnique;
import com.intrbiz.data.db.compiler.meta.SQLVersion;
import com.intrbiz.discuss.data.DiscussDB;

@SQLTable(schema = DiscussDB.class, name = "user", since = @SQLVersion({ 1, 0, 0 }))
@SQLUnique(name = "email_unq", columns = { "email" })
public class User implements Principal
{
    public static final int BCRYPT_WORK_FACTOR = 12;
    
    @SQLPrimaryKey()
    @SQLColumn(index = 1, name = "id", since = @SQLVersion({ 1, 0, 0 }))
    private UUID id;
    
    @SQLColumn(index = 2, name = "email", since = @SQLVersion({ 1, 0, 0 }))
    private String email;
    
    @SQLColumn(index = 3, name = "display_name", since = @SQLVersion({ 1, 0, 0 }))
    private String displayName;
    
    @SQLColumn(index = 4, name = "password_hash", since = @SQLVersion({ 1, 0, 0 }))
    private String passwordHash;
    
    @SQLColumn(index = 5, name = "role", since = @SQLVersion({ 1, 0, 0 }))
    private String role;
    
    @SQLColumn(index = 6, name = "registered", since = @SQLVersion({ 1, 0, 0 }))
    private Timestamp registered;
    
    @SQLColumn(index = 7, name = "locked", since = @SQLVersion({ 1, 2, 0 }))
    private boolean locked;
    
    @SQLColumn(index = 8, name = "verified", since = @SQLVersion({ 1, 2, 0 }))
    private boolean verified;
    
    @SQLColumn(index = 9, name = "verification_toke", since = @SQLVersion({ 1, 2, 0 }))
    private String verificationToken;
    
    @SQLColumn(index = 10, name = "force_password_change", since = @SQLVersion({ 1, 2, 0 }))
    private boolean forcePasswordChange;
    
    @SQLColumn(index = 11, name = "verified_at", since = @SQLVersion({ 1, 3, 0 }))
    private Timestamp verifiedAt;
    
    public User()
    {
        super();
    }
    
    public User(String email, String displayName, String password, String role)
    {
        super();
        this.id = UUID.randomUUID();
        this.email = email;
        this.displayName = displayName;
        this.hashPassword(password);
        this.role = role;
        this.registered = new Timestamp(System.currentTimeMillis());
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
        return this.email;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
    
    public void hashPassword(String plainPassword)
    {
        this.passwordHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_WORK_FACTOR));
    }

    public boolean verifyPassword(String plainPassword)
    {
        if (plainPassword == null || this.passwordHash == null) return false;
        return BCrypt.checkpw(plainPassword, this.passwordHash);
    }

    public Timestamp getRegistered()
    {
        return registered;
    }

    public void setRegistered(Timestamp registered)
    {
        this.registered = registered;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }

    public boolean isVerified()
    {
        return verified;
    }

    public void setVerified(boolean verified)
    {
        this.verified = verified;
    }

    public String getVerificationToken()
    {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken)
    {
        this.verificationToken = verificationToken;
    }

    public Timestamp getVerifiedAt()
    {
        return verifiedAt;
    }

    public void setVerifiedAt(Timestamp verifiedAt)
    {
        this.verifiedAt = verifiedAt;
    }

    public boolean isForcePasswordChange()
    {
        return forcePasswordChange;
    }

    public void setForcePasswordChange(boolean forcePasswordChange)
    {
        this.forcePasswordChange = forcePasswordChange;
    }
    
    public List<Discussion> getJoinedDiscussions()
    {
        try (DiscussDB db = DiscussDB.connect())
        {
            return db.getDiscussionsByUser(this.getId());
        }
    }
}

package com.intrbiz.bergamot.site.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("version-check")
public class VersionCheck extends APIResponse
{
    @JsonProperty("upto_date")
    private boolean uptoDate;
    
    @JsonProperty("current_version")
    private String currentVersion;
    
    public VersionCheck()
    {
        super();
    }
    
    public VersionCheck(boolean uptoDate, String currentVersion)
    {
        super(true);
        this.uptoDate = uptoDate;
        this.currentVersion = currentVersion;
    }

    public boolean isUptoDate()
    {
        return uptoDate;
    }

    public void setUptoDate(boolean uptoDate)
    {
        this.uptoDate = uptoDate;
    }

    public String getCurrentVersion()
    {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion)
    {
        this.currentVersion = currentVersion;
    }
}

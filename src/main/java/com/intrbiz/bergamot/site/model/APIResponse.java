package com.intrbiz.bergamot.site.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
public abstract class APIResponse
{
    @JsonProperty("stat")
    private String stat;
    
    public APIResponse()
    {
        super();
    }
    
    protected APIResponse(boolean stat)
    {
        super();
        this.stat = stat ? "OK" : "ERROR";
    }

    public String getStat()
    {
        return stat;
    }

    public void setStat(String stat)
    {
        this.stat = stat;
    }
}

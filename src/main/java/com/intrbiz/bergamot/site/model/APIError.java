package com.intrbiz.bergamot.site.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("error")
public class APIError extends APIResponse
{
    @JsonProperty("message")
    private String message;
    
    public APIError()
    {
        super();
    }
    
    public APIError(String message)
    {
        super(false);
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}

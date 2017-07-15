---
Author: Chris Ellis
Date: 2017-07-15
Code: bash
---
# Bergamot Monitoring API Overview

Bergamot Monitoring provides a HTTPS REST API which allows developers to perform all actions available in the user interface programatically.  The API allows you to get the state of checks, to manage alerts and to make configuration changes, all features in the UI have corresponding API resources.

The API follows HTTP semantics, valid requests be provided with a `200 OK` HTTP response and the body will be a JSON object (except for XML configuration resources).  Should something go wrong a `500 Internal Server Error` HTTP response will be provided with a JSON object describing the error.  Should a resource not exist then a `404 Not Found` HTTP response will be provided with a JSON object describing the missing resource.  All responses provide data at the top level, there is no first level `data` or `error` attribute.  All JSON objects will have a `type` attribute.

## Authenticating API Requests

API requests are authenticated using tokens.  You can generate a token on your profile page (follow 'Hello <your name>' from the main menu), you can provide a purpose to remind you in the future what the token is for.  Note that the auto login feature makes use of authentication tokens, so ensure you generate a dedicated token for your use case.  You can revoke authentication tokens at any point from your profile page.

Once you've generated an API token all you need to do is provide it as the value for the `Authorization` header.  For example:

    #> curl -H 'Authorization: 2cNJbNvMKNSSu1gUOdu8HuUjTjEAQTNbAFzsjuuaJX9kp7Q0Gu8rKWrA908Lpb2Fy0bisEq9GAl6Vdu2Ff952AOO7G4IBDTu-nc8Dntt' \
        https://demo.bergamot-monitoring.org/api/test/hello/you
    
    "Hello chris.ellis"

### Generating Temporary Authentication Tokens

You should treat any authentication tokens with care, you should not share these widely.  Anyone whom has you authentication token has the same level of access to Bergamot Monitoring as you do.  As such view an authentication token as a password.  However you can use your authentication token to generate a temporary authentication token which can be used for API requests, this token still has the same level of access, however it is only valid for 1 hour from the time of issue.

If you wish to integrate Bergamot Monitoring into your own web application then you must ensure you do not pass normal authentication tokens to the browser.  Instead your backend service should generate a temporary authentication token and pass that token to the browser.

You can generate a temporary authentication token using the `/api/auth-token` resource, as follows:

    #> curl -H 'Authorization: 2cNJbNvMKNSSu1gUOdu8HuUjTjEAQTNbAFzsjuuaJX9kp7Q0Gu8rKWrA908Lpb2Fy0bisEq9GAl6Vdu2Ff952AOO7G4IBDTu-nc8Dntt' \
        https://demo.bergamot-monitoring.org/api/auth-token
    {
        "type" : "bergamot.auth_token",
        "token" : "YZ2oyf8ku2kwZFOJHOFX-Ec69GLnK3kb-f5LyKI8G4meJk8Kr5tRqdSt4LOzr6Fu_Yg466ClE1DLMsdZ7DqUlGXmF0B23YtA1Po",
        "expires_at" : 1500157073037
    }

You cannot use a temporary authentication token to generate another temporary authentication token.

When integrating Bergamot Monitoring with another system it is strongly recommended to setup a dedicated user account from that application with restricted access, Bergamot Monitoring supports fine grained access controls so make use of them.


### Revoking Authentication Tokens

You can revoke your authentication tokens from your profile page.  As an administrator if you wish revoke '''ALL''' authentication tokens then you should change the `security-key` configuration value with-in the Bergamot Monitoring UI XML configuration file and restart all Bergamot Monitoring UI instances.

Note that revoking an authentication token will not revoke any temporary tokens which were generated from it.  However any temporary token is only valid for 1 hour.

## Testing API Connectivity

The API has a couple of test resources which purely exist for test purposes.

### Testing Unauthenticated Connectivity

The resource `/api/test/hello/world` will respond to unauthenticated requests, as follows:

    #> curl https://demo.bergamot-monitoring.org/api/test/hello/world; echo
    
    "Hello World"

This is useful for validating that a user has provided a valid Bergamot Monitoring URL.

### Testing Authenticated Connectivity

The resource `/api/test/hello/you` will respond with the authenticated users name, as follows:

    #> curl -H 'Authorization: 2cNJbNvMKNSSu1gUOdu8HuUjTjEAQTNbAFzsjuuaJX9kp7Q0Gu8rKWrA908Lpb2Fy0bisEq9GAl6Vdu2Ff952AOO7G4IBDTu-nc8Dntt' \
       https://demo.bergamot-monitoring.org/api/test/hello/you
    
    "Hello chris.ellis"

This is useful for validating that a user has provided a valid authentication token.

## What Now?

Take a look at the [API Reference](/docs/developer/api/reference) to see what you can do via the API.

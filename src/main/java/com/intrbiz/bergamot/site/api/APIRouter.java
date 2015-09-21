package com.intrbiz.bergamot.site.api;

import org.apache.log4j.Logger;

import com.intrbiz.balsa.engine.route.Router;
import com.intrbiz.balsa.http.HTTP.HTTPStatus;
import com.intrbiz.bergamot.site.BergamotSite;
import com.intrbiz.bergamot.site.model.APIError;
import com.intrbiz.bergamot.site.model.APIResponse;
import com.intrbiz.bergamot.site.model.VersionCheck;
import com.intrbiz.metadata.Any;
import com.intrbiz.metadata.Catch;
import com.intrbiz.metadata.Get;
import com.intrbiz.metadata.JSON;
import com.intrbiz.metadata.Param;
import com.intrbiz.metadata.Prefix;

@Prefix("/api")
public class APIRouter extends Router<BergamotSite>
{   
    public static final String CURRENT_VERSION = "2.0.0";
    
    private Logger logger = Logger.getLogger(APIRouter.class);
    
    @Get("/version/check")
    @JSON
    public APIResponse versionCheck(@Param("version") String currentVersion, @Param("site") String siteName)
    {
        logger.info("Version check: version=" + currentVersion + ", site=" + siteName + ", from=" + balsa().request().getRemoteAddress());
        response().header("Access-Control-Allow-Origin", "*");
        return new VersionCheck(CURRENT_VERSION.equalsIgnoreCase(currentVersion), CURRENT_VERSION);
    }
    
    @Catch()
    @Any("/**")
    @JSON(status = HTTPStatus.InternalServerError)
    public APIResponse apiError()
    {
        this.logger.error("Unhandled API error", balsa().getException());
        return new APIError("Unexpected API error");
    }
}

package authentication;

import controllers.CorsController;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

import java.util.UUID;

public class MyAuthenticator extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        String pid = ctx.session().get("pid");

        if (pid == null) {
            return null;
        }
        return pid;
    }

    @Override
    public Result onUnauthorized(Context ctx) {

        Http.Request request = ctx.request();
        String uri = request.uri();

        Http.Response response = ctx.response();
        response.setHeader("Referer", uri);
        String origin = ctx.request().getHeader("Origin");
        CorsController.addCorsHeaders(ctx.response(), origin);
        return unauthorized();
    }
}
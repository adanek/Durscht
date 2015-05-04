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
        return null;
    }

    @Override
    public Result onUnauthorized(Context ctx) {

        Http.Request request = ctx.request();
        //user is not authorized -> redirect to login form

        String uri = request.uri();

        Http.Response response = ctx.response();
        response.setHeader("Refferer", uri);
        CorsController.addCorsHeaders(ctx.response());
        return unauthorized();
    }
}
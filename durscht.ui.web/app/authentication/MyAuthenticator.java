package authentication;

import controllers.CorsController;
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

        //user is not authorized -> redirect to login form
        CorsController.addCorsHeaders(ctx.response());
        return unauthorized();
    }
}
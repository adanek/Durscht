package authentication;

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
        ctx.response().setHeader("Access-Control-Allow-Origin", "*");
        return unauthorized();
    }
}
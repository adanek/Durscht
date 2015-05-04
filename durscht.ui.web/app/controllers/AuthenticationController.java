package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.data.IUser;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;


public class AuthenticationController extends Controller {

    //GET /logout
    @Security.Authenticated(MyAuthenticator.class)
    public static Result logout() {

        String pid = session().get("pid");
        Logger.info(String.format("User %s has loged out.\n", pid));

        //clear session data
        session().clear();

        return ok();
    }

    //GET /user/id
    @Security.Authenticated(MyAuthenticator.class)
    public static Result getId(){

        int pid = Integer.parseInt(session().get("pid"));

        JsonNode data = Json.toJson(pid);
        CorsController.addCorsHeaders(response());
        return ok(data);
    }

    // POST /login
    public static Result login() {

        JsonNode data = request().body().asJson();
        String name = data.findPath("name").textValue();
        String password = data.findPath("pw").textValue();

        // Verify login data
        IUser user = ServiceLocator.getDataHandler().getUserLogin(name, password);

        //user does not exist or is unauthorized
        if (user == null) {
            return unauthorized();
        }

        //store session data
        session().clear();
        session().put("pid", Integer.toString(user.getId()));

        attachCorsHeaders();
        return ok();
    }

    private static void attachCorsHeaders() {
        CorsController.addCorsHeaders(response());
    }
}


package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.data.IUser;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;


public class AuthenticationController extends Controller {

    //GET /logout
    public static Result logout() {

        String pid = session().get("pid");
        Logger.info(String.format("User %s has loged out.\n", pid));

        //clear session data
        session().clear();

        return ok();
    }

    // POST /login
    public static Result login() {

        JsonNode data = request().body().asJson();
        String name = data.findPath("name").textValue();
        String password = data.findPath("pw").textValue();

        // Verify login data
        IUser user = ServiceLocator.getDataHandler().getUserLogin(name, password);

        //user does not exist or is unauthorized
//        if (user == null) {
//            return unauthorized();
//        }

        //store session data
        session().clear();
        session().put("pid", Integer.toString(42));
        attachCorsHeaders();
        return ok();
    }

    private static void attachCorsHeaders() {
        CorsController.addCorsHeaders(response());
    }
}


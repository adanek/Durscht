package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.logic.model.IUser;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.main;
import views.html.menu;

public class AuthenticationController extends Controller {

    //GET /user/id
    @Security.Authenticated(MyAuthenticator.class)
    public static Result getId() {

        int pid = Integer.parseInt(session().get("pid"));

        JsonNode data = Json.toJson(pid);
        CorsController.addCorsHeaders();
        return ok(data);
    }

    // POST /user/login
    public static Result login() {
        CorsController.addCorsHeaders();

        // Extract data from request
        JsonNode data = request().body().asJson();
        String name = data.findPath("name").textValue();
        String password = data.findPath("pw").textValue();

        // Verify login data
        Logger.info(String.format("User tried to login with name: %s and pw: %s.\n", name, password));
        IUser user = null;
        user = ServiceLocator.getLoginHandler().login(name, password);


        Logger.info("Returned from method login");
        //user does not exist or is unauthorized
        if (user == null) {
            Logger.info("No user found");
            return unauthorized();
        }

        //store session data
        session().clear();
        session().put("pid", Integer.toString(user.getId()));
        return ok();
    }

    //GET /logout
    @Security.Authenticated(MyAuthenticator.class)
    public static Result logout() {

        String pid = session().get("pid");
        Logger.info(String.format("User %s has loged out.\n", pid));

        //clear session data
        session().clear();

        CorsController.addCorsHeaders();
        return ok();
    }

    // POST /user/register
    public static Result register() {

        JsonNode data = request().body().asJson();
        String username = data.findPath("user").textValue();
        String email = data.findPath("email").textValue();
        String passwd = data.findPath("passwd").textValue();

        IUser user = null;
        user = ServiceLocator.getLoginHandler().createUser(username, passwd, email);

        session().clear();
        session().put("pid", Integer.toString(user.getId()));

        CorsController.addCorsHeaders();
        return ok();
    }

    public static Result adminLogin() {

        String email = request().body().asFormUrlEncoded().get("email")[0];
        String password = request().body().asFormUrlEncoded().get("password")[0];

        // Verify login data
        IUser user = null;
        user = ServiceLocator.getLoginHandler().adminLogin(email, password);

        //user does not exist or is unauthorized
        if (user == null) {
            return unauthorized(main.render("Unauthorisiert!"));
        }

        //store session data
        session().clear();
        session().put("pid", Integer.toString(user.getId()));

        return ok(menu.render());
    }
}


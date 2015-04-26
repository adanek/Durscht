package controllers;

import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(main.render());
    }

    public static Result CorsOptions(String url){
        response().setHeader("Access-Control-Allow-Origin", "*");
        response().setHeader("Access-Control-Request-Method","POST, GET, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials","true");
        return ok();
    }
}

package controllers;

import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(main.render());
    }

    public static Result CorsOptions(String url){
        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        response().setHeader("Access-Control-Request-Method","POST");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials","true");
        return ok();
    }
}

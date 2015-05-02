package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class CorsController extends Controller {

    public static Result CorsOptions(String url){

        addCorsHeaders(response());
        return ok();
    }

    public static void addCorsHeaders(Http.Response response){
        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        response().setHeader("Access-Control-Request-Method","POST, GET, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials","true");
    }
}

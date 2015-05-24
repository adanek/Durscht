package controllers;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class CorsController extends Controller {

    public static Result CorsOptions(String url){

        String origin = request().getHeader("Origin");
        addCorsHeaders(response(), origin);

        return ok();
    }

    public static void addCorsHeaders(Http.Response response){
        response().setHeader("Access-Control-Allow-Origin", "http://durscht.herokuapp.com");
        response().setHeader("Access-Control-Request-Method","POST, GET, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials", "true");
    }

    public static void addCorsHeaders(Http.Response response, String origin){
        response().setHeader("Access-Control-Allow-Origin", origin);
        response().setHeader("Access-Control-Request-Method","POST, GET, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials","true");
    }

    public static void addCorsHeaders(){

        String origin = request().getHeader("Origin");

        if(origin == null){
            Logger.info("Received request without ORIGIN");
            String host = request().remoteAddress();
            origin = String.format("http://%s", host);
        }

        response().setHeader("Access-Control-Allow-Origin", origin);
        response().setHeader("Access-Control-Request-Method","POST, GET, OPTIONS");
        response().setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
        response().setHeader("Access-Control-Allow-Credentials", "true");
    }
}

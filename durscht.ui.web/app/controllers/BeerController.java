package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Collection;


public class BeerController extends Controller {

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getAllBeers() {

        // Get all beers from BeerHandler
        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersVerified();

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

    //POST /beer/create
    public static Result create(){

        // Extract data from request
        JsonNode body = request().body().asJson();
        String brand = body.findPath("brand").textValue();
        String type = body.findPath("type").textValue();
        String description = body.findPath("description").textValue();

        // Create new beer
        IBeer beer = ServiceLocator.getBeerHandler().createNewBeer(brand, type, description);

        JsonNode data = Json.toJson(beer);
        attachCorsHeaders();
        return created(data);
    }

    public static Result getUsed(){

        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersVerified();

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

    private static void attachCorsHeaders() {

        String origin = request().getHeader("Origin");
        CorsController.addCorsHeaders(response(), origin);
    }
}

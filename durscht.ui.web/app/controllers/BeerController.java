package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;


public class BeerController extends Controller {

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getAllBeers() {

        // Get all beers from BeerHandler
        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersVerified();

        JsonNode data = Json.toJson(beers);
        CorsController.addCorsHeaders();
        return ok(data);
    }

    //POST /beer/create
    public static Result create() {

        // Extract data from request
        JsonNode body = request().body().asJson();
        String brand = body.findPath("brand").textValue();
        String type = body.findPath("type").textValue();
        String description = body.findPath("description").textValue();

        // Create new beer
        IBeer beer = ServiceLocator.getBeerHandler().createNewBeer(brand, type, description);

        JsonNode data = Json.toJson(beer);
        CorsController.addCorsHeaders();
        return created(data);
    }

    public static Result getUsed() {

        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersVerified();

        JsonNode data = Json.toJson(beers);
        CorsController.addCorsHeaders();
        return ok(data);
    }
}

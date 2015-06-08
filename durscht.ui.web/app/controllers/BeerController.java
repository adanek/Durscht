package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BeerController extends Controller {

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getAllBeers() {

        // Get all beers from BeerHandler
        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersVerified();
        IBeer[] beers2 = ServiceLocator.getBeerHandler().getAllBeersNotVerified();

        List<IBeer> bs = new ArrayList<>();
        bs.addAll(Arrays.asList(beers));
        bs.addAll(Arrays.asList(beers2));

        JsonNode data = Json.toJson(bs);
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

        List<IBeer> bs = new ArrayList<>();
        bs.addAll(Arrays.asList(beers));

        JsonNode data = Json.toJson(bs);
        CorsController.addCorsHeaders();
        return ok(data);
    }
}

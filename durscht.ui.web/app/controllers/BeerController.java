package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.data.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


public class BeerController extends Controller {


    //POST /beer/create
    public static Result create(){

        // Get data from request
        JsonNode body = request().body().asJson();
        String brand = body.findPath("brand").textValue();
        String type = body.findPath("type").textValue();
        String description = body.findPath("description").textValue();

        durscht.contracts.ui.IBeer beer = ServiceLocator.getBeerHandler().createNewBeer(brand, type, description);

        JsonNode data = Json.toJson(beer);
        attachCorsHeaders();
        return created(data);
    }

    private static void attachCorsHeaders() {

        String origin = request().getHeader("Origin");
        CorsController.addCorsHeaders(response(), origin);
    }
}

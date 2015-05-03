package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.data.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;


public class BeerController extends Controller {

    public static Result create(){

        // Get data from request
        JsonNode body = request().body().asJson();
        String brand = body.findPath("brand").textValue();
        String type = body.findPath("type").textValue();
        String description = body.findPath("description").textValue();

        // TODO: Replace this as soon BeerHandler provides method
        IBeer beer = ServiceLocator.getDataHandler().createBeer(brand, type, description);
        int id = beer.getId();

        JsonNode data = Json.toJson(id);
        CorsController.addCorsHeaders(response());
        return created(data);
    }
}

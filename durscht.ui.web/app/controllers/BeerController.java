package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Beer;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Collection;


public class BeerController extends Controller {

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getAllBeers() {

        // Replace this we Beerhandler as soon as its ready
        IDataHandler dh = ServiceLocator.getDataHandler();
        Collection<IBeer> beerList = dh.getAllBeers();
        durscht.contracts.data.IBeer[] allBeers = beerList.toArray(new durscht.contracts.data.IBeer[beerList.size()]);

        durscht.contracts.ui.IBeer[] beers = new durscht.contracts.ui.IBeer[allBeers.length];
        for(int i = 0; i < allBeers.length; i++){
            durscht.contracts.data.IBeer beer = allBeers[i];
            beers[i] = new Beer(beer.getId(), beer.getBrand(), beer.getType(), beer.getDescription());
        }

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

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

    public static Result getUsed(){

        durscht.contracts.ui.IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeers();

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

    private static void attachCorsHeaders() {

        String origin = request().getHeader("Origin");
        CorsController.addCorsHeaders(response(), origin);
    }
}

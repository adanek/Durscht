package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Beer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Collection;

public class ShareController extends Controller {

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getNearBars() {

        // Get parameters from request
        JsonNode jsonNode = request().body().asJson();
        double latitude = jsonNode.findPath("latitude").doubleValue();
        double longitude = jsonNode.findPath("longitude").doubleValue();


        IBar[] bars = new IBar[]{};
        try {
            ILogicFacade lf = ServiceLocator.getLogicFacade();
            IPostHandler postHandler = lf.getPostHandler();
            bars = postHandler.getNearBars(latitude, longitude);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonNode data = Json.toJson(bars);
        attachCorsHeaders();
        return ok(data);
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result createPost() {

        // Get data from request
        JsonNode data = request().body().asJson();
        int userId = data.findPath("user").intValue();
        int barId = data.findPath("bar").intValue();
        int beerId = data.findPath("beer").intValue();
        double prize = data.findPath("price").doubleValue();
        int rating = data.findPath("rating").intValue();
        String remark = data.findPath("remark").textValue();

        // Create new post
        IPostHandler postHandler = ServiceLocator.getLogicFacade().getPostHandler();
        postHandler.putPosting(barId, beerId, userId, prize, rating, remark);

        attachCorsHeaders();
        return created();
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result createBar() {

        // Extract data from request
        JsonNode data = request().body().asJson();
        String name = data.findPath("name").textValue();
        String url = data.findPath("web").textValue();
        String remark = data.findPath("remark").textValue();
        Double lng = data.findPath("longitude").doubleValue();
        Double lat = data.findPath("latitude").doubleValue();

        // Create a new bar
        IPostHandler postHandler = ServiceLocator.getLogicFacade().getPostHandler();
        IBar bar = postHandler.createNewBar(name, lat, lng, remark, url);

        JsonNode responseData = Json.toJson(bar);
        attachCorsHeaders();
        return created(responseData);
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result createBeer() {

        attachCorsHeaders();
        return created();
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getBeersFromBar(int barId){

        IBeer[] beers = ServiceLocator.getPostHandler().getBeersByBar(barId);

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getAllBeers() {

        // Replace this we Beerhandler as soon as its ready
        IDataHandler dh = ServiceLocator.getDataHandler();
        Collection<durscht.contracts.data.IBeer> beerList = dh.getAllBeers();
        durscht.contracts.data.IBeer[] allBeers = beerList.toArray(new durscht.contracts.data.IBeer[beerList.size()]);

        IBeer[] beers = new IBeer[allBeers.length];
        for(int i = 0; i < allBeers.length; i++){
            durscht.contracts.data.IBeer beer = allBeers[i];
            beers[i] = new Beer(beer.getId(), beer.getBrand(), beer.getType(), beer.getDescription());
        }

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
        return ok(data);
    }

    private static void attachCorsHeaders() {

        CorsController.addCorsHeaders(response());
    }
}

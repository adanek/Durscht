package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Bar;
import durscht.contracts.logic.IBarHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.logic.model.IBar;
import durscht.contracts.logic.model.IBeer;
import durscht.contracts.logic.model.IPost;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;


public class BarController extends Controller {

    public static Result getBarsWithBeers(){

        //List<IBar> bars = new ArrayList<IBar>();
        //bars.add(new Bar(14, "Wunderbar", 2.3, null, 47.269258, 11.4040792));
        //bars.add(new Bar(15, "Sonderbar", 5.1, null, 47.273216, 11.4422239));
        JsonNode data = request().body().asJson();
        double lat = data.findPath("latitude").doubleValue();
        double lng = data.findPath("longitude").doubleValue();

        List<Integer> beers = new ArrayList<>();
        final JsonNode beerIds = data.findPath("beers");

        for(JsonNode n : beerIds ){
            beers.add(n.intValue());
        }

        IBarHandler barHandler = ServiceLocator.getBarHandler();
        IBar[] bars = barHandler.findBars(lat, lng, beers);

        JsonNode responseData = Json.toJson(bars);
        CorsController.addCorsHeaders();
        return ok(responseData);
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
        IPostHandler postHandler = ServiceLocator.getPostHandler();
        IBarHandler barHandler = ServiceLocator.getBarHandler();
        //IBar bar = postHandler.createNewBar(name, lat, lng, remark, url);
        IBar bar = barHandler.createNewBar(name, lat, lng, remark, url);

        JsonNode responseData = Json.toJson(bar);
        CorsController.addCorsHeaders();
        return created(responseData);
    }

    public static Result getBeersFromBar(int barId){

        IBeer[] beers = ServiceLocator.getPostHandler().getBeersByBar(barId);

        JsonNode data = Json.toJson(beers);
        CorsController.addCorsHeaders();
        return ok(data);
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getNearBars() {

        // Extract parameters from request
        JsonNode jsonNode = request().body().asJson();
        double latitude = jsonNode.findPath("latitude").doubleValue();
        double longitude = jsonNode.findPath("longitude").doubleValue();


        IBar[] bars = new IBar[]{};
        try {
            IPostHandler postHandler = ServiceLocator.getPostHandler();
            bars = postHandler.getNearBars(latitude, longitude);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonNode data = Json.toJson(bars);
        CorsController.addCorsHeaders();
        return ok(data);
    }

    public static Result getPosts(Integer barId){

        IBarHandler barHandler = ServiceLocator.getBarHandler();
        IPost[] posts = barHandler.getPostsFromBar(barId);

        JsonNode data = Json.toJson(posts);
        CorsController.addCorsHeaders();
        return ok(data);
    }
}

package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Bar;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.logic.model.IBar;
import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.ArrayList;
import java.util.List;


public class BarController extends Controller {

    public static Result getBarsWithBeers(){

        List<IBar> bars = new ArrayList<IBar>();
        bars.add(new Bar(1, "Wunderbar", 2.3, null));
        bars.add(new Bar(2, "Sonderbar", 5.1, null));



        JsonNode data = Json.toJson(bars);
        attachCorsHeaders();
        return ok(data);
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
        IBar bar = postHandler.createNewBar(name, lat, lng, remark, url);

        JsonNode responseData = Json.toJson(bar);
        attachCorsHeaders();
        return created(responseData);
    }

    @Security.Authenticated(MyAuthenticator.class)
    public static Result getBeersFromBar(int barId){

        IBeer[] beers = ServiceLocator.getPostHandler().getBeersByBar(barId);

        JsonNode data = Json.toJson(beers);
        attachCorsHeaders();
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
        attachCorsHeaders();
        return ok(data);
    }


    private static void attachCorsHeaders() {

        String origin = request().getHeader("Origin");
        CorsController.addCorsHeaders(response(), origin);
    }
}

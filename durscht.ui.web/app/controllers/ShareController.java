package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Bar;
import controllers.mock.Beer;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.mvc.*;
import play.libs.Json;

public class ShareController extends Controller{

    public static Result getNearBars(){

        // Get parameters from request
        JsonNode jsonNode = request().body().asJson();
        double longitude = jsonNode.findPath("longitude").doubleValue();
        double latitude = jsonNode.findPath("latitude").doubleValue();

        IBar[] bars = ServiceLocator.getLogidFacade().getPostHandler().getNearBars(longitude, latitude);
        Logger.info(String.format("lng: %f lat: %f", longitude, latitude));
        // Create mock beers

//        IBeer[] noBeer = new Beer[0];
//        IBeer[] beers = new Beer[3];
//        beers[0] = new Beer(0,"Zipfer", "Märzen", "");
//        beers[1] = new Beer(1, "Stiegl", "Goldbräu", "");
//        beers[2] = new Beer(2, "Corona", "Extra", "");
//
//        // Create mock bars
//        IBar[] bars = new Bar[3];
//        bars[0] = new Bar(0, "Uferbar", 0.0, beers);
//        bars[1] = new Bar(1, "Wunderbar", 0.3, noBeer);
//        bars[2] = new Bar(2, "Sonderbar", 1.2, beers);

        JsonNode data = Json.toJson(bars);
        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        return ok(data);
    }

    public static Result createPost(){

        ILogicFacade lf = ServiceLocator.getLogidFacade();


        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        return created();
    }
}

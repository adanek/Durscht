package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.Bar;
import durscht.core.Beer;
import durscht.core.config.ServiceLocator;
import durscht.handler.DataHandler;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collection;

public class ShareController extends Controller {

    public static Result getNearBars() {

        // Get parameters from request
        JsonNode jsonNode = request().body().asJson();
        double longitude = jsonNode.findPath("longitude").doubleValue();
        double latitude = jsonNode.findPath("latitude").doubleValue();

        IBar[] bars = new IBar[]{};
        try {
            ILogicFacade lf = ServiceLocator.getLogidFacade();
            IPostHandler postHandler = lf.getPostHandler();
            //bars = postHandler.getNearBars(longitude, latitude);

            // Just do check the connection
            IDataHandler dH = ServiceLocator.getDataHandler();
            Collection<durscht.contracts.data.IBeer> beers = dH.getAllBeers();

            for(durscht.contracts.data.IBeer b : beers){
                System.out.println(String.format("Brand: %s", b.getName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JsonNode data = Json.toJson(bars);
        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        return ok(data);
    }

    public static Result createPost() {

        ILogicFacade lf = ServiceLocator.getLogidFacade();


        response().setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:9100");
        return created();
    }
}

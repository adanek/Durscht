package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.mock.Bar;
import controllers.mock.Beer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBeerHandler;
import durscht.contracts.logic.ILogicFacade;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.activation.DataHandler;
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
            bars = postHandler.getNearBars(longitude, latitude);

        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        bars = new IBar[3];
//        IBeer[] noBeer = new Beer[0];
//        IBeer[] beers = new Beer[3];
//        beers[0] = new Beer(0,"Zipfer", "Märzen", "");
//        beers[1] = new Beer(1, "Stiegl", "Goldbräu", "");
//        beers[2] = new Beer(2, "Corona", "Extra", "");
//
//        // Create mock bars
//        bars[0] = new Bar(0, "Uferbar", 0.0, beers);
//        bars[1] = new Bar(1, "Wunderbar", 0.3, noBeer);
//        bars[2] = new Bar(2, "Sonderbar", 1.2, beers);

        JsonNode data = Json.toJson(bars);
        attachCorsHeaders();
        return ok(data);
    }

    public static Result createPost() {

        JsonNode data = request().body().asJson();
        int userId = 1;
        int barId = data.findPath("bar").intValue();
        int beerId = data.findPath("beer").intValue();
        double prize = data.findPath("price").doubleValue();
        int rating = data.findPath("rating").intValue();
        String remark = data.findPath("remark").textValue();

        IPostHandler postHandler = ServiceLocator.getLogidFacade().getPostHandler();
        postHandler.putPosting(barId, beerId, userId, prize, rating, remark);

        attachCorsHeaders();
        return created();
    }

    public static Result createBar() {

        // Extract data from request
        JsonNode data = request().body().asJson();
        String name = data.findPath("name").textValue();
        String url = data.findPath("web").textValue();
        String remark = data.findPath("remark").textValue();
        Double lng = data.findPath("longitude").doubleValue();
        Double lat = data.findPath("latitude").doubleValue();

        // Create a new bar
        IPostHandler postHandler = ServiceLocator.getLogidFacade().getPostHandler();
        Integer newBar = postHandler.createNewBar(name, lat, lng, remark, url);
        System.out.println(newBar);

        IDataHandler dh = ServiceLocator.getDataHandler();
        durscht.contracts.data.IBar barData = dh.getBarByID(newBar);
        Bar bar = new Bar(barData.getId(), barData.getName(), 0.0, new IBeer[]{});

        JsonNode responseData = Json.toJson((IBar) bar);
        attachCorsHeaders();
        return created(responseData);
    }

    public static Result createBeer() {


        attachCorsHeaders();
        return created();
    }

    public static Result getAllBeers() {

        IBeer[] beers = new Beer[3];
        beers[0] = new Beer(0, "Zipfer", "Märzen", "");
        beers[1] = new Beer(1, "Stiegl", "Goldbräu", "");
        beers[2] = new Beer(2, "Corona", "Extra", "");

        IBeerHandler beerHandler = ServiceLocator.getLogidFacade().getBeerHandler();
        IDataHandler dh = ServiceLocator.getDataHandler();

        Collection<durscht.contracts.data.IBeer> allBeers = dh.getAllBeers();
        beers = new Beer[allBeers.size()];

        int i = 0;
        for (durscht.contracts.data.IBeer b : allBeers) {
            beers[i] = new Beer(b.getId(), b.getName(), "to be filled", b.getDescription());
            i++;
        }

        JsonNode data = Json.toJson(beers);

        attachCorsHeaders();
        return ok(data);
    }

    private static void attachCorsHeaders() {
        response().setHeader("Access-Control-Allow-Origin", "*");
    }
}

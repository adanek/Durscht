package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_beer;

public class AdminBeerController extends Controller{

    public static IBeer[] allBeers(){
        durscht.contracts.ui.IBeer [] beers = ServiceLocator.getBeerHandler().getAllBeers();
        return beers;
    }

    public static Result createBeer(){

        JsonNode body = request().body().asJson();
        String brand = body.findPath("brand").textValue();
        String type = body.findPath("type").textValue();
        String description = body.findPath("description").textValue();

//        String brand = request().body().asFormUrlEncoded().get("brand")[0];
//        String type = request().body().asFormUrlEncoded().get("type")[0];
//        String description = request().body().asFormUrlEncoded().get("description")[0];

        IBeer beer = ServiceLocator.getBeerHandler().createNewBeer(brand, type, description);

        JsonNode data = Json.toJson(beer);
        attachCorsHeaders();
        return created(data);
    }

    private static void attachCorsHeaders() {

        String origin = request().getHeader("Origin");
        CorsController.addCorsHeaders(response(), origin);
    }

    public static Result deleteBeer(int id){
        return redirect("/admin/beers");
    }
}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_beer;

public class AdminBeerController extends Controller{

    public static IBeer[] allBeers(){
        durscht.contracts.ui.IBeer [] beers = ServiceLocator.getBeerHandler().getAllBeers();
        return beers;
    }

    public static Result filterBeer(){

        String brand = request().body().asFormUrlEncoded().get("brand")[0];
        String type = request().body().asFormUrlEncoded().get("type")[0];

        durscht.contracts.ui.IBeer [] beers = ServiceLocator.getBeerHandler().getBeersByPrefix(brand + type);

        return ok(choose_beer.render(beers));
    }

    public static Result deleteBeer(int id){


        Logger.info(String.format("going to delete beer %d", id ));

        return redirect("/admin/beers");
    }
}

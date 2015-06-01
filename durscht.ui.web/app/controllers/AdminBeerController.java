package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import durscht.contracts.logic.model.IBeer;
import durscht.core.config.ServiceLocator;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_beer;

public class AdminBeerController extends Controller{

    public static IBeer[] allBeers(){
        IBeer[] beers = ServiceLocator.getBeerHandler().getAllBeersNotVerified();
        return beers;
    }

    public static Result filterBeer(){

        String brand = request().body().asFormUrlEncoded().get("brand")[0];
        String type = request().body().asFormUrlEncoded().get("type")[0];

        IBeer[] beers = ServiceLocator.getBeerHandler().getBeersByPrefix(brand + type);

        return ok(choose_beer.render(beers));
    }

    public static Result deleteBeer(int id){
        Logger.info(String.format("going to delete beer %d", id ));

        return redirect("/admin/beers");
    }

    public static Result verifyBeer(int id){
        //ServiceLocator.getBeerHandler().verifyBeer(id);

        return ok(choose_beer.render(allBeers()));
    }
}

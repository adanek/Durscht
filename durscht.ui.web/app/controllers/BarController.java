package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.ning.http.client.Request;

import controllers.mock.Bar;
import controllers.mock.Beer;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.config.ServiceLocator;
import model.Location;
import play.api.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class BarController extends Controller{
	
	
	// GET /bars/near
	
	public static Result getNear(){
		
		// Create mock beers 
		IBeer[] noBeer = new Beer[0];
		IBeer[] beers = new Beer[3];
		beers[0] = new Beer("Zipfer", "Märzen", "");
		beers[1] = new Beer("Stiegl", "Goldbräu", "");
		beers[2] = new Beer("Corona", "Extra", "");		
		
		// Create mock bars
		IBar[] bars = new Bar[3];
		bars[0] = new Bar(0, "Uferbar", 0.0, beers);
		bars[1] = new Bar(1, "Wunderbar", 0.3, noBeer);
		bars[2] = new Bar(2, "Sonderbar", 1.2, beers);		
		
		JsonNode data = Json.toJson(bars);
		response().setHeader("Access-Control-Allow-Origin", "*");
		return ok(data);
	}
	
	public static Result getNear2(){
		
//		double lng = Double.parseDouble(request().body().asFormUrlEncoded().get("longidute")[0]);
//		double lat = Double.parseDouble(request().body().asFormUrlEncoded().get("latidute")[0]);
//		
//		IPostHandler postHandler = ServiceLocator.getPostHandler();
//		
//		IBar[] nearBars = postHandler.getNearBars(lng, lat);
		
		// Create mock beers 
		Beer[] noBeer = new Beer[0];
		IBeer[] beers = new Beer[3];
		beers[0] = new Beer("Zipfer", "Märzen", "");
		beers[1] = new Beer("Stiegl", "Goldbräu", "");
		beers[2] = new Beer("Corona", "Extra", "");		
		
		// Create mock bars
		Bar[] bars = new Bar[3];
		bars[0] = new Bar(0, "Uferbar", 0.0, beers);
		bars[1] = new Bar(1, "Wunderbar", 0.3, noBeer);
		bars[2] = new Bar(2, "Sonderbar", 1.2, beers);		
		
		JsonNode data = Json.toJson(bars);
		response().setHeader("Access-Control-Allow-Origin", "*");
		return ok(data);
	}
}

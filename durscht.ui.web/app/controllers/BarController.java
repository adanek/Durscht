package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.mock.Bar;
import controllers.mock.Beer;
//import durscht.contracts.IBar;
//import durscht.contracts.IBeer;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class BarController extends Controller{
	
	
	// GET /bars/near
	public static Result getNear(){


		// Create mock beers 
		Beer[] noBeer = new Beer[0];
		Beer[] beers = new Beer[3];
		beers[0] = new Beer("Zipfer", "Märzen", "");
		beers[1] = new Beer("Stiegl", "Goldbräu", "");
		beers[2] = new Beer("Corona", "Extra", "");		
		
		// Create mock bars
		Bar[] bars = new Bar[3];
		bars[0] = new Bar(0, "Uferbar", 0.0, beers);
		bars[1] = new Bar(1, "Wunderbar", 0.3, noBeer);
		bars[2] = new Bar(2, "Sonderbar", 1.2, beers);		
		
		JsonNode data = Json.toJson(bars);
		return ok(data);
	}

}

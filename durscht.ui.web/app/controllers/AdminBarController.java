package controllers;

import controllers.mock.Bar;
import durscht.contracts.ui.IBar;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_bar;

import java.util.LinkedList;
import java.util.List;

public class AdminBarController extends Controller{


    public static Bar[] allBars(){
        Bar bar = new Bar(1, "name", 2.0, AdminBeerController.allBeers());
        Bar[] bars = {};
        return bars;
    }

    public static Result deleteBar(int id){
        return redirect("/admin/bars");
    }


}

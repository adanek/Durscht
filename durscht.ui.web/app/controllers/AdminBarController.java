package controllers;

import durscht.contracts.logic.model.IBar;
import durscht.core.config.ServiceLocator;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_bar;

import java.util.LinkedList;
import java.util.List;

public class AdminBarController extends Controller{


    public static IBar[] allBars(){
        IBar[] bars = ServiceLocator.getBarHandler().getAllBars();

        return bars;
    }

    public static Result deleteBar(int id){
        ServiceLocator.getBarHandler().deleteBar(id);

        return redirect("/admin/bars");
    }


}

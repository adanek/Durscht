package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_beer;
import views.html.choose_bar;
import views.html.choose_user;

public class MenuController extends Controller{

    public static Result chooseBeer(){
        return ok(choose_beer.render());
    }

    public static Result chooseBar(){
        return ok(choose_bar.render());
    }

    public static Result chooseUser(){
        return ok(choose_user.render());
    }


}

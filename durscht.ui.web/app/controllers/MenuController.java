package controllers;

import controllers.mock.Bar;
import controllers.mock.Beer;
import controllers.mock.Post;
import durscht.contracts.data.IBeerPost;

import durscht.core.BeerHandler;
import durscht.core.config.ServiceLocator;
import durscht.data.model.BeerPost;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Array;
import views.html.*;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MenuController extends Controller{

    public static Result showAllBeers(){
        return ok(choose_beer.render(AdminBeerController.allBeers()));
    }

    public static Result showAllBars(){
        return ok(choose_bar.render(AdminBarController.allBars()));
    }

    public static Result showAllPosts(){
        return ok(choose_post.render(AdminPostController.allPosts()));
    }

    public static Result showAllUsers(){
        return ok(choose_user.render(AdminUserController.allUsers()));
    }

    public static Result goToMenu(){
        return ok(menu.render());
    }

}

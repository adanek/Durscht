package controllers;

import controllers.mock.Bar;
import controllers.mock.Beer;
import controllers.mock.Post;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;
import durscht.core.BeerHandler;
import durscht.core.config.ServiceLocator;
import durscht.data.model.BeerPost;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Array;
import views.html.choose_beer;
import views.html.choose_bar;
import views.html.choose_user;
import views.html.choose_post;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MenuController extends Controller{

    public static Result allBeers(){



        durscht.contracts.ui.IBeer [] beers = ServiceLocator.getBeerHandler().getAllBeers();



        return ok(choose_beer.render(beers));

    }

    public static Result allPosts(){

        //durscht.data.model.Bar bar = new durscht.data.model.Bar();
        //bar.setName("Wunderbar");


        //durscht.data.model.BeerPost post;
        List<Post> posts = new LinkedList<>();
        posts.add(new Post("user1",4, "wunderbar", 2.0, "info",5, new Date(4,5,5)));
        posts.add(new Post("user1",4, "wunderbar", 2.0, "info",5, new Date(4,5,5)));

        return ok(choose_post.render(posts));
    }

    public static Result allBars(){

        //IBar[] nearBars = ServiceLocator.getPostHandler().getNearBars(47.0, 11.0);

        IBeer[] beers1;
        //beers1[1] = new IBeer()IBeer(2, "desc", "type", "brand");
        //IBeer beer = new IBeer(2, "desc", "type", "brand");



        //List<Bar> bars = new LinkedList<>();
       // bars.add(new Bar(1, "wunderbar", 4.0, beers1));

        return ok(choose_bar.render());
    }

    public static Result allUsers(){
        return ok(choose_user.render());
    }




    public static Result deletePost(int id){
        return redirect("/admin/posts");
    }

    public static Result deleteBeer(int id){
        return redirect("/admin/beers");
    }

    public static Result deleteBar(int id){
        return redirect("/admin/bars");
    }

    public static Result deleteUser(int id){
        return redirect("/admin/users");
    }





}

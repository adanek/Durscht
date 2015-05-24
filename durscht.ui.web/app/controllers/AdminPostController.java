package controllers;

import controllers.mock.Post;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_post;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AdminPostController extends Controller{

    public static List<Post> allPosts(){

        //durscht.data.model.Bar bar = new durscht.data.model.Bar();
        //bar.setName("Wunderbar");

        List<Post> posts = new LinkedList<>();
        posts.add(new Post("user1",4, "wunderbar", 2.0, "info",5, new Date(4,5,5)));
        posts.add(new Post("user1",4, "wunderbar", 2.0, "info",5, new Date(4,5,5)));

        return posts;
    }

    public static Result deletePost(int id){
        return redirect("/admin/posts");
    }

}

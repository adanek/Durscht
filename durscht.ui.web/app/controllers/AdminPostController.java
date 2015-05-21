package controllers;

import controllers.mock.Post;
import durscht.contracts.ui.IPost;
import durscht.core.config.ServiceLocator;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_post;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AdminPostController extends Controller{

    public static IPost [] allPosts(){
        durscht.contracts.ui.IPost [] posts = ServiceLocator.getPostHandler().getAllPosts();
        return posts;
    }

    public static Result deletePost(int id){
        return redirect("/admin/posts");
    }

}

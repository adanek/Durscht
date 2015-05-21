package controllers;

import controllers.mock.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.choose_user;

public class AdminUserController extends Controller{


    public static User[] allUsers(){

        User[] users = {};

        return users;
    }

    public static Result deleteUser(int id){
        return redirect("/admin/users");
    }
}

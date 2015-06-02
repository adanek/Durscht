package controllers;

import authentication.MyAuthenticator;
import com.fasterxml.jackson.databind.JsonNode;
import durscht.contracts.logic.IPostHandler;
import durscht.core.config.ServiceLocator;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

public class ShareController extends Controller {


    @Security.Authenticated(MyAuthenticator.class)
    public static Result createPost() {

        // Get data from request
        JsonNode data = request().body().asJson();
        int userId = data.findPath("user").intValue();
        int barId = data.findPath("bar").intValue();
        int beerId = data.findPath("beer").intValue();
        double prize = data.findPath("price").doubleValue();
        int rating = data.findPath("rating").intValue();
        String remark = data.findPath("remark").textValue();

        // Create new post
        IPostHandler postHandler = ServiceLocator.getLogicFacade().getPostHandler();
        postHandler.putPosting(barId, beerId, userId, prize, rating, remark);

        CorsController.addCorsHeaders();
        return created();
    }

}

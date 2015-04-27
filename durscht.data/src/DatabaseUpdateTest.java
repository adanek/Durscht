import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.data.IUser;
import durscht.handler.DataHandler;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

public class DatabaseUpdateTest {

	public static void main(String args[]) {

			try{
				//get handler
				IDataHandler handler  = new DataHandler();
				/*
				//create new users
				int userid1 = handler.createUser("admin", "admin@gmx.at", "admin");
				int userid2 = handler.createUser("user2", "user2@gmx.at", "user2");
				int userid3 = handler.createUser("user3", "user3@gmx.at", "user3");
	
				//create new beers
				int beerid1 = handler.createBeer("Gösser", "Herbes österreichisches Bier");
				int beerid2 = handler.createBeer("Stiegl", "Originales traditionelles Salzburger Bier");
				int beerid3 = handler.createBeer("Ottakringer", "Traditionelles Wiener Bier");
				int beerid4 = handler.createBeer("Starkenberger", "Heimisches Bier aus dem Schloss Starkenberg in Tarrenz");
				
				//create new Bars
				int barid1 = handler.createBar("Patis Testbar", 11.4040792, 47.269258, "geile Cocktails!!!", "Test");
				int barid2 = handler.createBar("11er Haus", 11.392825, 47.268653, "Sehr bekannte Bar in Innsbruck, die sehr viele Biersorten der ganzen Welt anbietet.", "http://innsbruckplus.at/elferhaus/");
				int barid3 = handler.createBar("Stadtcafe", 11.395963, 47.268785, "Bekanntestes Nachtlokal in Innsbruck, das hauptsächlich von der Studentenszene besucht wird.", "www.tagnacht.at/stadtcafe/");
				
				//create new achievement
				int achid1 = handler.createAchievement("10 posts", "The user will get this achievement when he drinks 10 beers and add a post to the app");
				int achid2 = handler.createAchievement("50 posts", "The user will get this achievement when he drinks 50 beers and add a post to the app");
				int achid3 = handler.createAchievement("100 posts", "The user will get this achievement when he drinks 100 beers and add a post to the app");
				
				int post1 = handler.createPost(barid1, beerid1, userid1, 3.0, 2, "Post1");
				int post2 = handler.createPost(barid1, beerid2, userid2, 3.0, 2, "Post2");
				int post3 = handler.createPost(barid2, beerid3, userid1, 3.0, 2, "Post3");
				int post4 = handler.createPost(barid2, beerid4, userid3, 3.0, 2, "Post4");
				
				IUser user = handler.getUserLogin("user2", "user2");
				
				Collection<IBeer> beers = handler.getAllBeers();
				Collection<IBeer> bars1 = handler.getAllBeersFromBar(barid1);
				Collection<IBeer> bars2 = handler.getAllBeersFromBar(barid2);
	
				Collection<IBeerPost> posts1 = handler.getAllPostsFromBar(barid1);
				Collection<IBeerPost> posts2 = handler.getAllPostsFromBar(barid2);
				Collection<IBeerPost> userposts1 = handler.getAllPostsFromUser(userid1);
				Collection<IBeerPost> userposts2 = handler.getAllPostsFromUser(userid2);
				*/
				Collection<IBar> bars = handler.getBarsCoordinates(47.2639834191332, 47.2639834191332, 11.3450129248849, 11.3450129248849);
			
				handler.closeDatabaseConnection();
			}catch(Exception e){}
	}

}
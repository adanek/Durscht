import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.data.IUser;
import durscht.handler.DataHandler;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

public class ToBeDeleted {

	public static void main(String args[]) {

			//get handler
			IDataHandler handler  = new DataHandler();
			
			//create new user
			int userid = handler.createUser("testUser4", "test.user3@gmx.at", "test12345678");

			//create new beer
			int beerid = handler.createBeer("TestBier2", "Das ist nur ein Testbier");
			//create new beer
			int beerid2 = handler.createBeer("TestBier3", "Das ist nur ein Testbier");

			IUser user = handler.getUserLogin("testUser4", "test12345678");
			
			int barid = handler.createBar("TheresienBräu", 13.0, 14.0, "", "");
			
			handler.createPost(barid, beerid, user.getId(), "hallo du");
			handler.createPost(barid, beerid2, user.getId(), "hallo er");
			
			Collection<IBeer> beers = handler.getAllBeersFromBar(barid);
			
			for(IBeer beer : beers){
				System.out.println(beer.getName());
			}
			

			handler.closeDatabaseConnection();
//			Bar bar1 = new Bar();
//			bar1.setName("Gössers");
//			Bar bar2 = new Bar();
//			bar2.setName("Stiegl Bräu");
//
//			Beer beer1 = new Beer();
//			beer1.setName("Stiegl");
//			Beer beer2 = new Beer();
//			beer2.setName("Starkenberger");
//
//			BeerPost post1 = new BeerPost();
//			post1.setUser(user1);
//			post1.setBar(bar1);
//			post1.setBeer(beer1);
//			BeerPost post2 = new BeerPost();
//			post2.setUser(user2);
//			post2.setBar(bar2);
//			post2.setBeer(beer2);
//
//			BeerPost post3 = new BeerPost();
//			post3.setUser(user2);
//			post3.setBar(bar1);
//			post3.setBeer(beer2);
//
//			user1.getBeerPosts().add(post1);
//			user2.getBeerPosts().add(post2);
//			user2.getBeerPosts().add(post3);
//			bar1.getBeerPosts().add(post1);
//			bar2.getBeerPosts().add(post2);
//			bar1.getBeerPosts().add(post3);
//			beer1.getBeerPosts().add(post1);
//			beer2.getBeerPosts().add(post2);
//			beer2.getBeerPosts().add(post3);

	}

}

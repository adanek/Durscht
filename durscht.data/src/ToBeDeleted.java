import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.IDataHandler;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

public class ToBeDeleted {

	public static void main(String args[]) {

			//get handler
			IDataHandler handler  = DataHandler.getHandler();
			
			//create new user
			handler.createUser("testUser", "test.user@gmx.at", "test1234");

			//create new beer
			handler.createBeer("TestBier", "Das ist nur ein Testbier");



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

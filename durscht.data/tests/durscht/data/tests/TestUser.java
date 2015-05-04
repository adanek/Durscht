package durscht.data.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IUser;
import durscht.data.handler.DataHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUser extends TestBase {

	@Test
	public void createUser() {

		// create user
		int id = dataHandler.createUser("TestUser", "test.user@gmx.at", "Test1234").getId();

		// get created user
		IUser user = dataHandler.getUserByID(id);

		// get current date
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String date = sdfDate.format(now);

		assertEquals("TestUser", user.getName());
		assertEquals("test.user@gmx.at", user.getEmail());
		assertEquals(date.toString(), user.getJoinedDate().toString());

	}

	@Test
	public void loginUser() {

		// login existing user
		IUser user = dataHandler.getUserLogin("TestUser", "Test1234");

		assertNotNull(user);

	}
	
	@Test
	public void getPostsFromUser(){
		
		// login existing user
		IUser user = dataHandler.getUserLogin("TestUser", "Test1234");
		
		// get all bars
		Collection<IBar> bars = dataHandler.getBarsCoordinates(-180.0, 180.0, -90.0, 90.0);

		// get first bar
		IBar bar = bars.iterator().next();
		
		// get all beers
		Collection<IBeer> beers = dataHandler.getAllBeers();

		// get first beer
		IBeer beer = beers.iterator().next();
		
		// create post
		 dataHandler.createPost(bar.getId(), beer.getId(), user.getId(), 3.0, 2,
				"Sehr gutes Bier").getId();
		
		//get all posts from user
		Collection<IBeerPost> posts = dataHandler.getAllPostsFromUser(user.getId());
		
		//posts collection must not be empty
		assertFalse(posts.isEmpty());
		
	}

}

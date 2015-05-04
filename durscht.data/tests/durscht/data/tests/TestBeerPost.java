package durscht.data.tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IUser;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBeerPost extends TestBase {

	@Test
	public void createBeerPost() {

		// get all bars
		Collection<IBar> bars = dataHandler.getBarsCoordinates(-180.0, 180.0, -90.0, 90.0);

		// get first bar
		IBar bar = bars.iterator().next();

		// get all beers
		Collection<IBeer> beers = dataHandler.getAllBeers();

		// get first beer
		IBeer beer = beers.iterator().next();
		
		// create user
		dataHandler.createUser("TestUser", "test.user@gmx.at", "Test1234").getId();

		// login user
		IUser user = dataHandler.getUserLogin("TestUser", "Test1234");

		// create post
		int id = dataHandler.createPost(bar.getId(), beer.getId(), user.getId(), 3.0, 2,
				"Sehr gutes Bier").getId();
		
		// get all posts for bar
		Collection<IBeerPost> posts = dataHandler.getAllPostsFromBar(bar.getId());

		IBeerPost post = null;

		Iterator<IBeerPost> iterator = posts.iterator();
		while (posts.iterator().hasNext()) {

			// get next post
			post = iterator.next();

			if (post == null) {
				break;
			}

			// correct post?
			if (post.getId() == id) {
				break;
			}
		}

		assertNotNull(post);
		assertEquals(id, post.getId());

	}
	
	@Test
	public void getAllBeersFromBar(){
		
		// get all bars
		Collection<IBar> bars = dataHandler.getBarsCoordinates(-180.0, 180.0, -90.0, 90.0);

		// get first bar
		IBar bar = bars.iterator().next();

		//get all beers from bar
		Collection<IBeer> beers2 = dataHandler.getAllBeersFromBar(bar.getId());
		
		//beers2 collection must not be empty
		assertFalse(beers2.isEmpty());

	}
	
	@Test
	public void getAllPostsFromBar(){
		
		// get all bars
		Collection<IBar> bars = dataHandler.getBarsCoordinates(-180.0, 180.0, -90.0, 90.0);

		// get first bar
		IBar bar = bars.iterator().next();

		Collection<IBeerPost> posts = dataHandler.getAllPostsFromBar(bar.getId());
		
		//posts collection must not be empty
		assertFalse(posts.isEmpty());

	}

}

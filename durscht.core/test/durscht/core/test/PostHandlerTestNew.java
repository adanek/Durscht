package durscht.core.test;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.core.PostHandler;
import durscht.data.model.Bar;
import durscht.data.model.Beer;
import durscht.data.model.BeerPost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class PostHandlerTestNew {

	private PostHandler postHandler;

	@Before
	public void setup() {
		postHandler = new PostHandler();
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBarsTooLargeLatitudeTest() {
		postHandler.getNearBars(91, 20);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBarsTooSmallLatitudeTest() {
		postHandler.getNearBars(-91, 20);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBarsTooLargeLongitudeTest() {
		postHandler.getNearBars(20, 191);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getBarsTooSmallLongitudeTest() {
		postHandler.getNearBars(20, -191);
	}

	/*
	 * Deprecated
	 * 
	 * @Test (expected = NoSuchElementException.class) public void getBarsNoSuchElementTest() { IDataHandler dataHandler
	 * = Mockito.mock(IDataHandler.class);
	 * Mockito.when(dataHandler.getBarsCoordinates(Mockito.anyDouble(),Mockito.anyDouble(),
	 * Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(null); postHandler.setDataHandler(dataHandler);
	 * 
	 * postHandler.getNearBars(47, 11); }
	 */

	@Test
	public void getNearBarsTest() {
		Collection<IBar> bars = new ArrayList<IBar>();
		Bar bar = new Bar();
		bar.setId(26);
		bar.setName("TechCafe");
		bars.add(bar);

		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(
				dataHandler.getBarsCoordinates(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble(),
						Mockito.anyDouble())).thenReturn(bars);
		postHandler.setDataHandler(dataHandler);

		durscht.contracts.logic.model.IBar ibar = postHandler.getNearBars(47, 11)[0];

		assertEquals(26, ibar.getId());
		assertEquals("TechCafe", ibar.getName());
	}

	@Test
	public void getBeersByBarTest() {
		Collection<IBeer> beers = new ArrayList<IBeer>();
		Beer beer = new Beer();
		beer.setId(10);
		beer.setBrand("Zipfer");
		beer.setType("Märzen");
		beers.add(beer);

		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getAllBeersFromBar(26)).thenReturn(beers);
		postHandler.setDataHandler(dataHandler);

		durscht.contracts.logic.model.IBeer ibeer = postHandler.getBeersByBar(26)[0];

		int expected = 10;
		assertEquals(expected, ibeer.getId());
		assertEquals("Zipfer", ibeer.getBrand());
		assertEquals("Märzen", ibeer.getType());

	}

	// will fail! IBeerPost returned!
	@Test
	public void TestputPostingTest() {

		BeerPost beerPost = new BeerPost();
		beerPost.setId(26);

		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.createPost(0, 0, 0, 2.0, 2, "test")).thenReturn(beerPost);
		postHandler.setDataHandler(dataHandler);

		int expected = 26;
		int actual = postHandler.putPosting(0, 0, 0, 2.0, 2, "test");

		assertEquals(expected, actual);
	}

}

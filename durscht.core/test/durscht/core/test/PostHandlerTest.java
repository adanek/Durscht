package durscht.core.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.core.PostHandler;
import durscht.data.model.Bar;
import durscht.data.model.Beer;

public class PostHandlerTest {

	private PostHandler postHandler;
	
	@Before
	public void setup() {
		postHandler = new PostHandler();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void getBarsTooLargeLatitudeTest() {
		postHandler.getNearBars(91, 20);
	}

	@Test (expected = IllegalArgumentException.class)
	public void getBarsTooSmallLatitudeTest() {
		postHandler.getNearBars(-91, 20);
	}

	@Test (expected = IllegalArgumentException.class)
	public void getBarsTooLargeLongitudeTest() {
		postHandler.getNearBars(20, 191);
	}

	@Test (expected = IllegalArgumentException.class)
	public void getBarsTooSmallLongitudeTest() {
		postHandler.getNearBars(20, -191);
	}

	/* Deprecated
	@Test (expected = NoSuchElementException.class)
	public void getBarsNoSuchElementTest() {
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getBarsCoordinates(Mockito.anyDouble(),Mockito.anyDouble(),
				Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(null);
		postHandler.setDataHandler(dataHandler);
		
		postHandler.getNearBars(47, 11);
	}
	*/
	
	@Test
	public void getNearBarsTest() {
		Collection<IBar> bars = new ArrayList<IBar>();
		Bar bar = new Bar();
		bar.setId(26);
		bar.setName("TechCafe");
		bars.add(bar);
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getBarsCoordinates(Mockito.anyDouble(),Mockito.anyDouble(),
				Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(bars);
		postHandler.setDataHandler(dataHandler);
		
		durscht.contracts.ui.IBar ibar = postHandler.getNearBars(47, 11)[0];
		
		assertEquals(26, ibar.getId());
		assertEquals("TechCafe", ibar.getName());
	}
	
	@Test
	public void getBeersByBarTest() {
		Collection<IBeer> beers = new ArrayList<IBeer>();
		Beer beer = new Beer();
		beer.setId(10);
		beer.setBrand("Zipfer");
		beer.setType("M�rzen");
		beers.add(beer);
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getAllBeersFromBar(26)).thenReturn(beers);
		
		durscht.contracts.ui.IBeer ibeer = postHandler.getBeersByBar(26)[0];
		
		assertEquals(10, ibeer.getId());
		assertEquals("Zipfer", ibeer.getBrand());
		assertEquals("M�rzen", ibeer.getType());
		
	}
	
	// will fail! IBeerPost returned!
	@Test
	public void putPostingTest() {
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.createPost(0, 0, 0, 2.0, 2, "test")).thenReturn(26);
		postHandler.setDataHandler(dataHandler);
		
		int expected = 26;
		int actual = postHandler.putPosting(0, 0, 0, 2.0, 2, "test");
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void createNewBarTest() {
		Bar bar = new Bar();
		bar.setId(26);
		bar.setName("barname");
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.createBar("barname", 0, 0, "descr", "www.bar.at")).thenReturn(bar);
		
		durscht.contracts.ui.IBar ibar = postHandler.createNewBar("barname", 0, 0, "descr", "www.bar.at");
		
		assertEquals(26, ibar.getId());
		assertEquals("barname", ibar.getName());
	}

}

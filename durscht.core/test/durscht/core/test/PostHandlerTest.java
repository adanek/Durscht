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
import durscht.model.Bar;
import durscht.model.Beer;

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

	@Test (expected = NoSuchElementException.class)
	public void getBarsNoSuchElementTest() {
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getBarsCoordinates(Mockito.anyDouble(),Mockito.anyDouble(),
				Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(null);
		postHandler.setDataHandler(dataHandler);
		
		postHandler.getNearBars(47, 11);
	}
	
	@Test
	public void getNearBars() {
		Collection<IBar> bars = new ArrayList<IBar>();
		Collection<IBeer> beers = new ArrayList<IBeer>();
		Bar bar = new Bar();
		bar.setId(26);
		bars.add(bar);
		
		Beer beer = new Beer();
		beers.add(beer);
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.getBarsCoordinates(Mockito.anyDouble(),Mockito.anyDouble(),
				Mockito.anyDouble(),Mockito.anyDouble())).thenReturn(bars);
		Mockito.when(dataHandler.getAllBeersFromBar(26)).thenReturn(beers);
		postHandler.setDataHandler(dataHandler);
		
		int expected = 26;
		int actual = postHandler.getNearBars(47, 11)[0].getId();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void putPostingTest() {
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.createPost(0, 0, 0, 2.0, 2, "test")).thenReturn(26);
		postHandler.setDataHandler(dataHandler);
		
		int expected = 26;
		int actual = postHandler.putPosting(0, 0, 0, 2.0, 2, "test");
		
		assertEquals(expected,actual);
	}

}

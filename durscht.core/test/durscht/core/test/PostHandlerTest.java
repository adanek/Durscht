package durscht.core.test;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import durscht.contracts.data.IDataHandler;
import durscht.core.PostHandler;

public class PostHandlerTest {

	private PostHandler postHandler;
	
	@Before
	public void setup() {
		postHandler = new PostHandler();
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void getBarsInvalidArgumentTest() {
		postHandler.getNearBars(100, 100);
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
	public void putPostingTest() {
		
		IDataHandler dataHandler = Mockito.mock(IDataHandler.class);
		Mockito.when(dataHandler.createPost(0, 0, 0, "test")).thenReturn(26);
		postHandler.setDataHandler(dataHandler);
		
		int want = 26;
		int have = postHandler.putPosting(0, 0, 0, "test");
		
		assertEquals(want,have);
	}

}

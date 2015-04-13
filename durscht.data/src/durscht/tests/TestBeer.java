package durscht.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.handler.*;

public class TestBeer {

	private static IDataHandler dataHandler;
	
	@BeforeClass
	public static void BeforeClass(){
		
	  //get data handler
	  dataHandler = DataHandler.getHandler();
	  
	}
	
	@Test
	public void createBeer() {
		
		int id = dataHandler.createBeer("Zipfer", "teuer!");
		
		IBeer beer = dataHandler.getBeerByID(id);
		
		assertEquals("Zipfer", beer.getName());
		assertEquals("teuer!", beer.getDescription());
	}

}

package durscht.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.handler.*;

public class TestBeer extends TestBase {

	@Test
	public void createBeer() {
		
		//create beer
		int id = dataHandler.createBeer("Zipfer", "teuer!");
		
		//get created beer
		IBeer beer = dataHandler.getBeerByID(id);
		
		assertEquals("Zipfer", beer.getName());
		assertEquals("teuer!", beer.getDescription());
	}

}

package durscht.data.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.data.handler.*;

public class TestBeer extends TestBase {

	@Test
	public void createBeer() {

		// create beer
		int id = dataHandler.createBeer("Zipfer", "Märzen", "teuer!").getId();

		// get created beer
		IBeer beer = dataHandler.getBeerByID(id);

		assertEquals("Zipfer", beer.getBrand());
		assertEquals("Märzen", beer.getType());
		assertEquals("teuer!", beer.getDescription());
	}

}

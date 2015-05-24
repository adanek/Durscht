package durscht.data.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.data.handler.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBeer extends TestBase {

	@Test
	public void createBeer() {

		// create beer
		int id = dataHandler.createBeer("Zipfer", "Märzen", "teuer!", false).getId();

		// get created beer
		IBeer beer = dataHandler.getBeerByID(id);
	
		assertEquals("Zipfer", beer.getBrand());
		assertEquals("Märzen", beer.getType());
		assertEquals("teuer!", beer.getDescription());
		assertEquals(false, beer.isVerified());
	}
	
	@Test
	public void getAllBeers(){
		
		//get all beers
		Collection<IBeer> beers = dataHandler.getAllBeers();
		
		//collection size must be greater than 0
		assertNotEquals(0, beers.size());
		
	}
	
}

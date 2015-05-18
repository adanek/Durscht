package durscht.data.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IDataHandler;
import durscht.data.handler.DataHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBar extends TestBase {

	@Test
	public void createBar() {

		// create bar
		int id = dataHandler.createBar("Gössers", 11.392206, 47.266619, "Bar", "www.goessers.at", true)
				.getId();

		// get created bar
		IBar bar = dataHandler.getBarByID(id);

		assertEquals(id, bar.getId());
		assertEquals("Gössers", bar.getName());
		assertEquals(11.392206, bar.getLatitude(), 1e-15);
		assertEquals(47.266619, bar.getLongitude(), 1e-15);
		assertEquals("Bar", bar.getDescription());
		assertEquals("www.goessers.at", bar.getUrl());

	}

	@Test
	public void getBarsCoordinates(){
		
		//get all bars within certain range
		Collection<IBar> bars = dataHandler.getBarsCoordinates(11.0, 12.0, 47.0, 48.0);
		
		//bars collection must not be empty
		assertFalse(bars.isEmpty());
		
	}
	
}

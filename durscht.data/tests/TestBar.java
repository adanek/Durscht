package durscht.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IDataHandler;
import durscht.handler.DataHandler;

public class TestBar extends TestBase {

	@Test
	public void createBar() {

		// create bar
		int id = dataHandler.createBar("G�ssers", 47.266619, 11.392206, "Bar", "www.goessers.at")
				.getId();

		// get created bar
		IBar bar = dataHandler.getBarByID(id);

		assertEquals(id, bar.getId());
		assertEquals("G�ssers", bar.getName());
		assertEquals(47.266619, bar.getLatitude(), 1e-15);
		assertEquals(11.392206, bar.getLongitude(), 1e-15);
		assertEquals("Bar", bar.getDescription());
		assertEquals("www.goessers.at", bar.getUrl());

	}

}

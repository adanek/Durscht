package durscht.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IDataHandler;
import durscht.handler.DataHandler;

public class TestBar {

	private static IDataHandler dataHandler;
	
	@BeforeClass
	public static void BeforeClass(){
		
	  //get data handler
	  dataHandler = DataHandler.getHandler();
	  
	}
	
	@Test
	public void createBar() {
		
		assertTrue(true);
		
	}

}

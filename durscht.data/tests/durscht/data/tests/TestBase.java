package durscht.data.tests;

import static org.junit.Assert.*;

import org.hibernate.service.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IDataHandler;
import durscht.data.handler.DataHandler;

public class TestBase {

	// dataHandler instance for all test classes
	protected static IDataHandler dataHandler;

	@BeforeClass
	public static void init() {

		// create only one instance
		if (dataHandler == null) {
			dataHandler = new DataHandler();
		}

	}

}

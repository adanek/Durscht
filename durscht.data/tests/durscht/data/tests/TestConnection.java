package durscht.data.tests;

import static org.junit.Assert.*;

import org.hibernate.service.UnknownServiceException;
import org.junit.Test;

public class TestConnection extends TestBase {

	@Test(expected = UnknownServiceException.class)
	public void closeConnection() {

		// close DB connection
		dataHandler.closeDatabaseConnection();

		try {
			// method should throw an exception
			dataHandler.getUserLogin("TestUser", "TestPW");
		} catch (Exception e) {
			// dataHandler reset to get a new connection for the remaining tests
			dataHandler = null;
			throw e;
		}

	}

}

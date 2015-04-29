package durscht.data.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import durscht.contracts.data.IUser;
import durscht.data.handler.DataHandler;

public class TestUser extends TestBase {

	@Test
	public void createUser() {

		// create user
		int id = dataHandler.createUser("TestUser", "test.user@gmx.at", "Test1234").getId();

		// get created user
		IUser user = dataHandler.getUserByID(id);

		// get current date
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String date = sdfDate.format(now);

		assertEquals("TestUser", user.getName());
		assertEquals("test.user@gmx.at", user.getEmail());
		assertEquals(date.toString(), user.getJoinedDate().toString());

	}

	@Test
	public void loginUser() {

		// login existing user
		IUser user = dataHandler.getUserLogin("TestUser", "Test1234");

		assertNotNull(user);

	}

}

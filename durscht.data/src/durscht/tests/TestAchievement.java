package durscht.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IDataHandler;
import durscht.handler.DataHandler;

public class TestAchievement extends TestBase {

	@Test
	public void createAchievement() {

		// create new achievement
		int id = dataHandler.createAchievement("erster Bierpost",
				"bekommt der User f�r seinen ersten Post").getId();

		// get created achievement
		IAchievement ach = dataHandler.getAchievementByID(id);

		assertEquals(id, ach.getId());
		assertEquals("erster Bierpost", ach.getName());
		assertEquals("bekommt der User f�r seinen ersten Post", ach.getDescription());

	}

}

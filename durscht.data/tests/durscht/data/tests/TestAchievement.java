package durscht.data.tests;

import static org.junit.Assert.*;

import org.hibernate.service.UnknownServiceException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IDataHandler;
import durscht.data.handler.DataHandler;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAchievement extends TestBase {

	@Test
	public void createAchievement() {

		// create new achievement
		int id = dataHandler.createAchievement("erster Bierpost",
				"bekommt der User für seinen ersten Post").getId();

		// get created achievement
		IAchievement ach = dataHandler.getAchievementByID(id);
		
		assertEquals(id, ach.getId());
		assertEquals("erster Bierpost", ach.getName());
		assertEquals("bekommt der User für seinen ersten Post", ach.getDescription());

	}
	
}

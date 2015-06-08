package durscht.contracts.logic;

import durscht.contracts.logic.model.IAchievement;
import durscht.contracts.logic.model.IBar;
import durscht.contracts.logic.model.IBeer;
import durscht.contracts.logic.model.IPost;

public interface IPostHandler {

	/**
	 * Provides an IBar-array of bars around a given center-point
	 * 
	 * @param latitude Latitude of center-point
	 * @param longitude Longitude of center-point
	 * @return Array of Bars around the passed point
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude
	 *         outside [-90,90]
	 * @throws IllegalStateException data base errors
	 */
	IBar[] getNearBars(double latitude, double longitude) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Looks for a given bar in the database for all its beers.
	 * 
	 * @param barID ID of the bar whose beers are looked up
	 * @return Array of beers registered for a given bar
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	IBeer[] getBeersByBar(int barID) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Saves the passed information as a Post in the database
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param prize
	 * @param rating
	 * @param description
	 * @return index of the saved post
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	Integer putPosting(int barID, int beerID, int userID, double prize, int rating, String description)
			throws IllegalArgumentException, IllegalStateException;

	/**
	 * Returns all Posts from the database
	 * 
	 * @return All Posts from the database
	 * @throws IllegalStateException database errors
	 */
	IPost[] getAllPosts() throws IllegalStateException;

	/**
	 * Deletes a post from the database.
	 * 
	 * @param postID ID of the post to be deleted.
	 * @throws IllegalArgumentException database errors
	 */
	void deletePost(int postID) throws IllegalArgumentException;

	/**
	 * Returns all achievements a user receives for his posts. (update before internally)
	 * 
	 * @param userID ID of the user whose achievements are returned
	 * @return Achievement-array denoting what achievements a user gained
	 * @throws IllegalArgumentException wrong userID
	 * @throws IllegalStateException database error
	 */
	IAchievement[] getAchievementsByUser(int userID) throws IllegalArgumentException, IllegalStateException;
}

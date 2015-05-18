package durscht.contracts.logic;

import java.util.List;

import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IPost;

public interface IBarHandler {

	/**
	 * Returns all bars in your neighboring environment which serves at least one of your favorite beers
	 * 
	 * @param latitude Current latitude, center-point of search
	 * @param longitude Current longitude, center-point of search
	 * @param favoriteBeers List of beers to search for
	 * @return List of Bars within 10km which serve at least one of favoriteBeers
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude
	 *         outside [-90,90], empty favoriteBeers list
	 * @throws IllegalStateException data base errors
	 */
	IBar[] findBars(double latitude, double longitude, List<Integer> favoriteBeers) throws IllegalArgumentException,
			IllegalStateException;

	/**
	 * Returns an array of all bars saved in the database
	 * 
	 * @return Array of all bars
	 * @throws IllegalStateException database errors
	 */
	IBar[] getAllBars() throws IllegalStateException;

	/**
	 * Returns all Posts for a specific bar
	 * 
	 * @param barID ID of the bar whose posts are returned
	 * @return Array of posts for a given bar
	 * @throws IllegalArgumentException no bar with ID barID found in database.
	 * @throws IllegalStateException database errors.
	 */
	IPost[] getPostsFromBar(int barID) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Deletes a bar from the database.
	 * 
	 * @param barID ID of the bar to be deleted.
	 * @throws IllegalArgumentException database errors.
	 */
	void deleteBar(int barID) throws IllegalArgumentException;
}
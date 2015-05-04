package durscht.contracts.logic;

import durscht.contracts.ui.IBar;
import durscht.contracts.ui.IBeer;

public interface IPostHandler {

	/**
	 * Provides an IBar-array of bars around a given center-point
	 * 
	 * @param latitude Latitude of center-point
	 * @param longitude Longitude of center-point
	 * @return Array of Bars around the passed point
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude
	 *         outside [-90,90]
	 */
	public IBar[] getNearBars(double latitude, double longitude) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Looks for a given bar in the database for all its beers.
	 * 
	 * @param bar The bar whose beers are looked up
	 * @return Array of beers registerd for a given bar
	 */
	public IBeer[] getBeersByBar(int barID) throws IllegalArgumentException, IllegalStateException;

	/**
	 * Saves the passed information as a Post in the database
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param description
	 * @return index of the created post
	 */
	public Integer putPosting(int barID, int beerID, int userID, double prize, int rating, String description)
			throws IllegalArgumentException, IllegalStateException;

	/**
	 * Creates a new Bar in the database
	 * 
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param description
	 * @param url
	 * @return
	 */
	public IBar createNewBar(String name, double latitude, double longitude, String description, String url)
			throws IllegalStateException;
}

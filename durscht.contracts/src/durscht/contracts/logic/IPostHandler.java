package durscht.contracts.logic;

import durscht.contracts.ui.IBar;


public interface IPostHandler {
	
	/**
	 * Provides an IBar-array of bars around a given center-point
	 * 
	 * @param latitude Latitude of center-point
	 * @param longitude Longitude of center-point
	 * @return Array of Bars around the passed point
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude outside [-90,90]
	 */
	public IBar[] getNearBars(double latitude, double longitude) throws IllegalArgumentException;
	
	/**
	 * Saves the passed information as a Post in the database
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param description
	 * @return index of the created post
	 */
	public Integer putPosting(int barID, int beerID, int userID, double prize, int rating, String description);
	
	
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
	public Integer createNewBar(String name, double latitude, double longitude, String description, String url);
}

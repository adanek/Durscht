package durscht.contracts.logic;

import java.util.NoSuchElementException;

import durscht.contracts.ui.IBar;


public interface IPostHandler {
	
	/**
	 * 
	 * @param longitude Latitude of center-point
	 * @param latitude Longitude of center-point
	 * @return Array of Bars around the passed point
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude outside [-90,90]
	 * @throws NoSuchElementException if no bar was found in the database around the center-point
	 */
	public IBar[] getNearBars(double longitude, double latitude) throws IllegalArgumentException, NoSuchElementException;
	
	/**
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param description
	 * @return index of the created post
	 */
	public Integer putPosting(int barID, int beerID, int userID, String description) throws NullPointerException;
	
	
	/**
	 * 
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param description
	 * @param url
	 * @return
	 */
	public Integer createNewBar(String name, double latitude, double longitude, String description, String url) throws NullPointerException;
}

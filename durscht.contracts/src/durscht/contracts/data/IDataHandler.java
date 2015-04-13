package durscht.contracts.data;

import java.util.Collection;

import durscht.contracts.data.IBeer;

public interface IDataHandler {

	public int createUser(String name, String email, String password);
	
	public int createBeer(String name, String description);
	
	public int createBar(String name, double latitude, double longitude, String description, String url);
	
	public int createPost(int barID, int beerID, int userID, String descripton);
	
	/**
	 * search for a User by name and password
	 * @return User or null when no user exists in the database with this name and password
	 */
	public IUser getUserLogin(String name, String password);
	
	/**
	 * get all beers that are saved in the database
	 * @return all beers
	 */
	public Collection<IBeer> getAllBeers();
	
	/**
	 * get all bars that are between the longitude and latitude coordinates
	 * @param fromLatitude
	 * @param toLatitude
	 * @param fromLongitude
	 * @param toLongitude
	 * @return	list of all matched bars or null when it is no bar between the coordinates
	 */
	public Collection<IBar> getBarsCoordinates(double fromLatitude, double toLatitude, double fromLongitude, double toLongitude);
	
	/**
	 * get all beers that are in this bar available
	 * @param barID
	 * @return	list of beers or null if no beers are available
	 */
	public Collection<IBeer> getAllBeersFromBar(int barID);
	
	/**
	 * get all posts from a bar
	 * @param barID
	 * @return a list of posts or null if no post is in the database
	 */
	public Collection<IBeerPost> getAllPostsFromBar(int barID);
	
	/**
	 * disconnect database connection
	 */
	public void closeDatabaseConnection();
	
	/** 
	 * get beer by id
	 * @param id Beer ID
	 * @return
	 */
	public IBeer getBeerByID(int id);	
}

package durscht.contracts.data;

import java.util.Collection;

import durscht.contracts.data.IBeer;

/**
 * interface for communication with logic
 * @author Witsch Daniel. Deutsch Patrick
 *
 */
public interface IDataHandler {

	public Integer createUser(String name, String email, String password);
	
	public Integer createBeer(String name, String description);
	
	public Integer createBar(String name, double latitude, double longitude, String description, String url);
	
	public Integer createPost(int barID, int beerID, int userID, String description);
	
	public Integer createAchievement(String name, String description);
	
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
	 * get all posts from a a user
	 * @param userID
	 * @return a list of posts or null if no post is in the database
	 */
	public Collection<IBeerPost> getAllPostsFromUser(int userID);
	
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
	
	/** 
	 * get bar by id
	 * @param id Bar ID
	 * @return
	 */
	public IBar getBarByID(int id);	
	
	/** 
	 * get achievement by id
	 * @param id Achievement ID
	 * @return
	 */
	public IAchievement getAchievementByID(int id);
	
	/** 
	 * get user by id
	 * @param id User ID
	 * @return
	 */
	public IUser getUserByID(int id);
}

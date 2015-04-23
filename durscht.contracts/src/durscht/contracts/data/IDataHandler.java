package durscht.contracts.data;

import java.util.Collection;

import durscht.contracts.data.IBeer;

/**
 * interface for communication with logic
 * @author Witsch Daniel, Deutsch Patrick
 *
 */
public interface IDataHandler {

	/**
	 * create an new user
	 * @return the ID of the created object or null if the creation failed
	 */
	public Integer createUser(String name, String email, String password);
	
	/**
	 * deletes a user from the database
	 * @param userID
	 */
	public boolean deleteUser(int userID);
	
	/**
	 * create a new beer
	 * @return the ID of the created object or null if the creation failed
	 */
	public Integer createBeer(String name, String description);
	
	/**
	 * deletes a beer from the database
	 * @param beerID
	 */
	public boolean deleteBeer(int beerID);
	
	/**
	 * create a new bar in the database
	 * @param latitude 
	 * @param longitude
	 * @param description
	 * @param url optional, if no url is given then add "" to the method
	 * @return the ID of the created object or null if the creation failed
	 */
	public Integer createBar(String name, double latitude, double longitude, String description, String url);
	
	/**
	 * deletes a bar from the database
	 * @param barID
	 */
	public boolean deleteBar(int barID);
	
	/**
	 * create a new Post
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param descripton
	 * @return ID of post or null when creation failed, one possibility is that one of the IDs (bar,beer,user) doesn't exists 
	 */
	public Integer createPost(int barID, int beerID, int userID, double price, int rating, String description);
	
	/**
	 * deletes a beer post
	 * @param postID
	 */
	public boolean deletePost(int postID);
	
	/**
	 * create a new achievement in the database
	 * @param name
	 * @param description
	 * @return the ID of the created object or null if the creation failed
	 */
	public Integer createAchievement(String name, String description);
	
	/**
	 * deletes an achievement
	 * @param aID
	 */
	public boolean deleteAchievement(int aID);
	
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
	 * get all posts from a user
	 * @param userID
	 * @return a list of posts or null if no post is in the database
	 */
	public Collection<IBeerPost> getAllPostsFromUser(int userID);
	
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
	
	
	/**
	 * disconnect database connection
	 */
	public void closeDatabaseConnection();
}

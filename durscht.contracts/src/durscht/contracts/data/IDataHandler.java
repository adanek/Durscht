package durscht.contracts.data;

import java.util.Collection;

/**
 * interface for communication with logic
 * 
 * @author Witsch Daniel, Deutsch Patrick
 *
 */
public interface IDataHandler {

	/**
	 * create an new user
	 * 
	 * @return the ID of the created object or null if the creation failed
	 * @throws IllegalStateException
	 *             creating object in database or hashing of the password failed
	 */
	public IUser createUser(String name, String email, String password)
			throws IllegalStateException;

	/**
	 * deletes a user from the database
	 * 
	 * @param userID
	 * @throws IllegalArgumentException
	 *             deletion or getting user from ID failed
	 */
	public void deleteUser(int userID) throws IllegalArgumentException;

	/**
	 * create a new beer
	 * 
	 * @return the ID of the created object or null if the creation failed
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	public IBeer createBeer(String name, String description)
			throws IllegalStateException;

	/**
	 * deletes a beer from the database
	 * 
	 * @param beerID
	 * @throws IllegalArgumentException
	 *             deletion or getting beer from ID failed
	 */
	public void deleteBeer(int beerID) throws IllegalArgumentException;

	/**
	 * create a new bar in the database
	 * 
	 * @param latitude
	 * @param longitude
	 * @param description
	 * @param url
	 *            optional, if no url is given then add "" to the method
	 * @return the new created object bar
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	public IBar createBar(String name, double latitude, double longitude,
			String description, String url) throws IllegalStateException;

	/**
	 * deletes a bar from the database
	 * 
	 * @param barID
	 * @throws IllegalArgumentException
	 *             deletion or getting bar from ID failed
	 */
	public void deleteBar(int barID) throws IllegalArgumentException;

	/**
	 * create a new Post
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param price
	 * @param rating
	 * @param descripton
	 * @return ID of post or null when creation failed, one possibility is that
	 *         one of the IDs (bar,beer,user) doesn't exists
	 * @throws IllegalStateException
	 *             saving from post failed
	 * @throws IllegalArgumentException
	 *             searching for beer, bar or user failed
	 */
	public IBeerPost createPost(int barID, int beerID, int userID, double price,
			int rating, String descripton) throws IllegalStateException,
			IllegalArgumentException;

/*	//**
	 * deletes a post from the database
	 * 
	 * @param postID
	 * @throws IllegalArgumentException
	 *             deletion or getting post from ID failed
	 *//*
	public void deletePost(int postID) throws IllegalArgumentException;*/

	/**
	 * create a new achievement in the database
	 * 
	 * @param name
	 * @param description
	 * @return the ID of the created object or null if the creation failed
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	public IAchievement createAchievement(String name, String description)
			throws IllegalStateException;

	/**
	 * deletes a achievement from the database
	 * 
	 * @param aID
	 * @throws IllegalArgumentException
	 *             deletion or getting achievement from ID failed
	 */
	public void deleteAchievement(int aID) throws IllegalArgumentException;

	/**
	 * search for a User by name and password
	 * 
	 * @return User or null when no user exists in the database with this name
	 *         and password
	 */
	public IUser getUserLogin(String name, String password);

	/**
	 * get all beers that are saved in the database
	 * 
	 * @return all beers or null if something with database failed
	 */
	public Collection<IBeer> getAllBeers();

	/**
	 * get all bars that are between the longitude and latitude coordinates
	 * 
	 * @param fromLatitude
	 * @param toLatitude
	 * @param fromLongitude
	 * @param toLongitude
	 * @return list of all matched bars or null if something with database failed
	 */
	public Collection<IBar> getBarsCoordinates(double fromLatitude,
			double toLatitude, double fromLongitude, double toLongitude);

	/**
	 * get all beers that are in this bar available
	 * 
	 * @param barID
	 * @return list of beers
	 * @throws IllegalArgumentException
	 *             barID not in database
	 */
	public Collection<IBeer> getAllBeersFromBar(int barID) throws IllegalArgumentException;

	/**
	 * get all posts from a bar
	 * 
	 * @param barID
	 * @return a list of posts
	 * @throws IllegalArgumentException
	 *             barID not in database
	 */
	public Collection<IBeerPost> getAllPostsFromBar(int barID)
			throws IllegalArgumentException;

	/**
	 * get all posts from a user
	 * 
	 * @param userID
	 * @return a list of posts
	 * @throws IllegalArgumentException
	 *             userID not in database
	 */
	public Collection<IBeerPost> getAllPostsFromUser(int userID)
			throws IllegalArgumentException;

	/**
	 * search for a beer by ID
	 * 
	 * @return Beer or null when no beer exists in the database with this ID
	 * @throws IllegalArgumentException
	 *             beer with this ID not in database
	 */
	public IBeer getBeerByID(int id) throws IllegalArgumentException;

	/**
	 * search for a bar by ID
	 * 
	 * @return Bar or null when no user exists in the database with this ID
	 * @throws IllegalArgumentException
	 *             bar with this ID not in database
	 */
	public IBar getBarByID(int id) throws IllegalArgumentException;

	/**
	 * search for a achievement by ID
	 * 
	 * @return Achievement or null when no user exists in the database with this
	 *         ID
	 * @throws IllegalArgumentException
	 *             achievement with this ID not in database
	 */
	public IAchievement getAchievementByID(int id) throws IllegalArgumentException;

	/**
	 * search for a user by ID
	 * 
	 * @return User or null when no user exists in the database with this ID
	 * @throws IllegalArgumentException
	 *             user with this ID not in database
	 */
	public IUser getUserByID(int id) throws IllegalArgumentException;

	/**
	 * search for a beer post by ID
	 * 
	 * @return BeerPost or null when no beer post exists in the database with
	 *         this ID
	 * @throws IllegalArgumentException
	 *             post with this ID not in database
	 */
	public IBeerPost getPostByID(int id) throws IllegalArgumentException;

	/**
	 * disconnect database connection
	 * 
	 * @throws IllegalStateException
	 *             if closing connection to database not possible
	 */
	public void closeDatabaseConnection() throws IllegalStateException;
}

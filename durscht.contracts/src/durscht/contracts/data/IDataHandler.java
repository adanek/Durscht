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
	 * @return the new created user
	 * @throws IllegalStateException
	 *             creating object in database or hashing of the password failed
	 */
	IUser createUser(String name, String email, String password, boolean admin)
			throws IllegalStateException;

	/**
	 * create a new beer
	 * 
	 * @param verified
	 *            verified or not yet
	 * @return the new created beer
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	IBeer createBeer(String brand, String type, String description, boolean verified)
			throws IllegalStateException;

	/**
	 * create a new bar in the database
	 * 
	 * @param latitude
	 * @param longitude
	 * @param description
	 * @param url
	 *            optional, if no url is given then add "" to the method
	 * @return the new created bar
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	IBar createBar(String name, double latitude, double longitude, String description, String url)
			throws IllegalStateException;

	/**
	 * create a new achievement in the database
	 * 
	 * @param name
	 * @param description
	 * @return the new created achievement
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	IAchievement createAchievement(String name, String description, int criterionID) throws IllegalStateException;

	/**
	 * create a new achievement criterion in the database
	 * 
	 * @param type
	 * @param value
	 * @return the new created achievement criterion
	 * @throws IllegalStateException
	 *             creating object in database failed
	 */
	IAchievementCriterion createAchievementCriterion(
			AchievementCriterionType type, int value)
			throws IllegalStateException;
	
	/**
	 * create a new Post
	 * 
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param price
	 * @param rating
	 * @param descripton
	 * @return the new created BeerPost
	 * @throws IllegalStateException
	 *             saving from post failed
	 * @throws IllegalArgumentException
	 *             searching for beer, bar or user failed
	 */
	IBeerPost createPost(int barID, int beerID, int userID, double price, int rating,
			String descripton) throws IllegalStateException, IllegalArgumentException;

	/**
	 * deletes a user from the database
	 * 
	 * @param userID
	 * @throws IllegalArgumentException
	 *             deletion or getting user from ID failed
	 */
	void deleteUser(int userID) throws IllegalArgumentException;

	/**
	 * deletes a beer from the database
	 * 
	 * @param beerID
	 * @throws IllegalArgumentException
	 *             deletion or getting beer from ID failed
	 */
	void deleteBeer(int beerID) throws IllegalArgumentException;

	/**
	 * deletes a bar from the database
	 * 
	 * @param barID
	 * @throws IllegalArgumentException
	 *             deletion or getting bar from ID failed
	 */
	void deleteBar(int barID) throws IllegalArgumentException;

	/**
	 * deletes a post from the database
	 * 
	 * @param postID
	 * @throws IllegalArgumentException
	 *             deletion or getting post from ID failed
	 */
	void deletePost(int postID) throws IllegalArgumentException;

	/**
	 * assign achievement to a user
	 * 
	 * @return User new User object
	 * @throws IllegalArgumentException
	 *             user or achievement with this ID not in database, which
	 *             argument is wrong is reported in the exception message, or
	 *             achievement has user already
	 * @throws IllegalStateException
	 *             database error
	 */
	IUser assignAchievementToUser(int userID, int achID) throws IllegalArgumentException,
			IllegalStateException;

	/**
	 * verify a beer
	 * 
	 * @return the verified IBeer object
	 * @throws IllegalArgumentException
	 *             beer with this ID not in database
	 * @throws IllegalStateException
	 *             database error
	 */
	IBeer verifyBeer(int beerID) throws IllegalArgumentException, IllegalStateException;

	/**
	 * get all users that are saved in the database
	 * 
	 * @return all beers
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IUser> getAllUsers() throws IllegalStateException;

	/**
	 * get all beers that are saved in the database
	 * 
	 * @return all beers
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBeer> getAllBeers() throws IllegalStateException;

	/**
	 * get all beers that are saved in the database and are verified
	 * 
	 * @return all beers that are verified
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBeer> getAllBeersVerified() throws IllegalStateException;

	/**
	 * get all bars that are saved in the database
	 * 
	 * @return all bars
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBar> getAllBars() throws IllegalStateException;

	/**
	 * get all achievements that are saved in the database
	 * 
	 * @return all achievements
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IAchievement> getAllAchievements() throws IllegalStateException;

	/**
	 * get all Posts from the database
	 * 
	 * @return Collection of posts or a empty list if no posts are in the
	 *         database
	 * @throws IllegalArgumentException
	 *             database error
	 */
	Collection<IBeerPost> getAllPosts() throws IllegalStateException;

	/**
	 * search for a user by ID
	 * 
	 * @return User
	 * @throws IllegalArgumentException
	 *             user with this ID not in database
	 */
	IUser getUserByID(int id) throws IllegalArgumentException;

	/**
	 * search for a beer by ID
	 * 
	 * @return Beer
	 * @throws IllegalArgumentException
	 *             beer with this ID not in database
	 */
	IBeer getBeerByID(int id) throws IllegalArgumentException;

	/**
	 * search for a bar by ID
	 * 
	 * @return Bar
	 * @throws IllegalArgumentException
	 *             bar with this ID not in database
	 */
	IBar getBarByID(int id) throws IllegalArgumentException;

	/**
	 * search for a achievement by ID
	 * 
	 * @return Achievement ID
	 * @throws IllegalArgumentException
	 *             achievement with this ID not in database
	 */
	IAchievement getAchievementByID(int id) throws IllegalArgumentException;

	/**
	 * search for a beer post by ID
	 * 
	 * @return BeerPost this ID
	 * @throws IllegalArgumentException
	 *             post with this ID not in database
	 */
	IBeerPost getPostByID(int id) throws IllegalArgumentException;

	/**
	 * search for a User by name and password
	 * 
	 * @return User or null when no user exists in the database with this name
	 *         and password
	 * @throws IllegalStateException
	 *             database error
	 */
	IUser getUserLogin(String name, String password) throws IllegalStateException;

	/**
	 * search for a User by name and password and with admin rights
	 * 
	 * @return User or null when no user exists in the database with this name
	 *         and password
	 * @throws IllegalStateException
	 *             database error
	 */
	IUser getUserLoginAdmin(String name, String password) throws IllegalStateException;

	/**
	 * get all bars that are between the longitude and latitude coordinates
	 * 
	 * @param fromLatitude
	 * @param toLatitude
	 * @param fromLongitude
	 * @param toLongitude
	 * @return list of all matched bars
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBar> getBarsCoordinates(double fromLatitude, double toLatitude,
			double fromLongitude, double toLongitude) throws IllegalStateException;

	/**
	 * get all achievements from a user
	 * 
	 * @param userID
	 * @return a list of achievements
	 * @throws IllegalArgumentException
	 *             userID not in database
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IAchievement> getAllAchievementsFromUser(int userID)
			throws IllegalArgumentException, IllegalStateException;

	/**
	 * get all posts from a user
	 * 
	 * @param userID
	 * @return a list of posts
	 * @throws IllegalArgumentException
	 *             userID not in database
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBeerPost> getAllPostsFromUser(int userID) throws IllegalArgumentException;

	/**
	 * get all beers that are in this bar available
	 * 
	 * @param barID
	 * @return list of beers or an empty list if no beer was found
	 * @throws IllegalArgumentException
	 *             barID not in database
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBeer> getAllBeersFromBar(int barID) throws IllegalArgumentException,
			IllegalStateException;

	/**
	 * get all posts from a bar
	 * 
	 * @param barID
	 * @return a list of posts
	 * @throws IllegalArgumentException
	 *             barID not in database
	 * @throws IllegalStateException
	 *             database error
	 */
	Collection<IBeerPost> getAllPostsFromBar(int barID) throws IllegalArgumentException,
			IllegalStateException;

	/**
	 * search bars by coordinates and beers
	 * 
	 * @return Collection of bars
	 * @throws IllegalArgumentException
	 *             post with this ID not in database
	 */
	Collection<IBar> findBars(double fromLatitude, double toLatitude, double fromLongitude,
			double toLongitude, Collection<Integer> beerIDs) throws IllegalStateException;

	/**
	 * disconnect database connection
	 * 
	 * @throws IllegalStateException
	 *             if closing connection to database not possible
	 */
	void closeDatabaseConnection() throws IllegalStateException;
}

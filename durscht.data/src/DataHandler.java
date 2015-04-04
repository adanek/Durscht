import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import durscht.contracts.IAchievement;
import durscht.contracts.IBar;
import durscht.contracts.IBeer;
import durscht.contracts.IBeerPost;
import durscht.contracts.IDataHandler;
import durscht.contracts.IUser;
import durscht.model.Achievement;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

/**
 * communication interface between the core and the data layer of our project
 * class to handle all database interactions
 * 
 * @author Witsch Daniel, Deutsch Patrick
 *
 */
public class DataHandler implements IDataHandler {

	// handler instance
	private static IDataHandler instance;
	// session factory
	private SessionFactory sessionFactory;

	// constructor
	private DataHandler() {

		try {
			// connect to db
			connectToDatabase();

			// create session factory
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Connection to Database failed");
			e.printStackTrace();
		}
	}

	// get instance handler
	public static IDataHandler getHandler() {

		// create instance
		if (instance == null) {
			instance = new DataHandler();
		}

		// return instance
		return instance;

	}

	/**
	 * disconnect database connection
	 */
	public void closeDatabaseConnection() {
		sessionFactory.close();
		instance = null;
	}

	// get database connection
	private Connection connectToDatabase() throws URISyntaxException,
			SQLException {

		URI dbUri = new URI(
				"postgres://lncxojfprcynxw:YqnYBdYl9e5JweBKBFIIP3n48M@ec2-54-163-228-58.compute-1.amazonaws.com:5432/d5nj2e3kege64a");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://"
				+ dbUri.getHost()
				+ ':'
				+ dbUri.getPort()
				+ dbUri.getPath()
				+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		return DriverManager.getConnection(dbUrl, username, password);
	}

	// open a new session
	private Session openSession() {

		return sessionFactory.openSession();
	}

	// save a new object in the database
	private Integer saveObjectToDb(Object obj) {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// save an object
			int id = (int) session.save(obj);

			// commit
			session.getTransaction().commit();

			return id;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}

	}
	
	/**
	 * get an Object from the Database with the id
	 * @param id
	 * @param typeParameterClass Class type of the searched class for example (Bar.class)
	 * @return	Object with the corresponding id
	 */
	private <T> T searchForID(int id, Class<T> typeParameterClass) {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(typeParameterClass);
			cr.add(Restrictions.eq("id", id));
			List<T> results = cr.list();

			// commit
			session.getTransaction().commit();

			//only one element in the list because the id is unique
			return results.get(0);

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}

	}

	/**
	 * create an new user
	 */
	public int createUser(String name, String email, String password) {

		// create user instance
		SavedUser user = new SavedUser();
		user.setEmail(email);
		user.setJoinedDate(new Date());
		user.setName(name);
		try {
			user.setPassword(PasswordHash.getSaltedHash(password));
		} catch (Exception e) {
			System.out.println("Creation of Hash failed");
		}

		// save user in database
		return saveObjectToDb(user);

	}

	/**
	 * create a new beer
	 */
	public int createBeer(String name, String description) {

		// create beer instance
		Beer beer = new Beer();
		beer.setName(name);
		beer.setDescription(description);

		// save beer to database
		return saveObjectToDb(beer);

	}
	
	/**
	 * create a new bar in the database
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param description
	 * @param url
	 * @return the ID of the created object or null if the creation failed
	 */
	public int createBar(String name, double latitude, double longitude, String description, String url){
		
		//create bar instance
		Bar bar = new Bar();
		bar.setName(name);
		bar.setLatitude(latitude);
		bar.setLongitude(longitude);
		bar.setDescription(description);
		bar.setUrl(url);
		
		//save bar to database
		return saveObjectToDb(bar);
	}
	
	/**
	 * create a new achievement in the database
	 * @param name
	 * @param description
	 * @return the ID of the created object or null if the creation failed
	 */
	public int createAchievement(String name, String description){
		//create achievement instance
		Achievement ach = new Achievement();
		ach.setName(name);
		ach.setDescription(description);
		
		//save achievement to database
		return saveObjectToDb(ach);
	}
	
	/**
	 * search for a User by name and password
	 * @return User or null when no user exists in the database with this name and password
	 */
	public IUser getUserLogin(String name, String password){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("name", name));
			List<SavedUser> results = cr.list();

			// commit
			session.getTransaction().commit();

			//only one element in the list because the id is unique
			for(SavedUser user : results){
				try {
	                if (PasswordHash.check(password, user.getPassword()))
	                    return user;
	            } catch (Exception e) {
	                System.out.println("Fail by checking the user password");
	            }
			}
			return null;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}
	}
	
	/**
	 * search for a user by ID
	 * @return User or null when no user exists in the database with this ID
	 */
	private SavedUser getUserByID(int id){
		return this.<SavedUser>searchForID(id, SavedUser.class);
	}
	
	/**
	 * search for a bar by ID
	 * @return Bar or null when no user exists in the database with this ID
	 */
	private Bar getBarByID(int id){
		return this.<Bar>searchForID(id, Bar.class);
	}
	
	/**
	 * search for a beer by ID
	 * @return Beer or null when no user exists in the database with this ID
	 */
	private Beer getBeerByID(int id){
		return this.<Beer>searchForID(id, Beer.class);
	}
	
	/**
	 * search for a achievement by ID
	 * @return Achievement or null when no user exists in the database with this ID
	 */
	private Achievement getAchievementByID(int id){
		return this.<Achievement>searchForID(id, Achievement.class);
	}
	
	/**
	 * get all beers that are saved in the database
	 * @return all beers
	 */
	public Collection<IBeer> getAllBeers(){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Beer.class);
			List<IBeer> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}
	}
	
	/**
	 * get all bars that are between the longitude and latitude coordinates
	 * @param fromLatitude
	 * @param toLatitude
	 * @param fromLongitude
	 * @param toLongitude
	 * @return	list of all matched bars or null when it is no bar between the coordinates
	 */
	public Collection<IBar> getBarsCoordinates(double fromLatitude, double toLatitude, double fromLongitude, double toLongitude){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.between("latitude", fromLatitude, toLatitude));
			cr.add(Restrictions.between("longitude", fromLongitude, toLongitude));
			List<IBar> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}
	}
	
	/**
	 * get all beers that are in this bar available
	 * @param barID
	 * @return	list of beers or null if no beers are available
	 */
	public Collection<IBeer> getAllBeersFromBar(int barID){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			List<Bar> results = cr.list();

			if(results == null)
				return null;	//bar not found with this id
	
			
			Collection<BeerPost> posts = results.get(0).getBeerPosts();
			Collection<IBeer> beers = new ArrayList<>();
			
			for(BeerPost post : posts){
				beers.add(post.getBeer());
			}
			
			// commit
			session.getTransaction().commit();
			
			return beers;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}
	}

	/**
	 * get all posts from a bar
	 * @param barID
	 * @return a list of posts or null if no post is in the database
	 */
	public Collection<IBeerPost> getAllPostsFromBar(int barID){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			List<Bar> results = cr.list();

			if(results == null)
				return null;	//bar not found with this id
	
			
			Collection<BeerPost> posts = results.get(0).getBeerPosts();
			
			Collection<IBeerPost> ret = new LinkedList<>(posts);
						
			// commit
			session.getTransaction().commit();
			
			return ret;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return null;
		} finally {
			// close session
			session.close();
		}
	}
	/**
	 * create a new Post
	 * @param barID
	 * @param beerID
	 * @param userID
	 * @param descripton
	 * @return ID of post or 0 when creation failed
	 */
	public int createPost(int barID, int beerID, int userID, String descripton){
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			Bar bar = (Bar) cr.list().get(0);
			
			cr = session.createCriteria(Beer.class);
			cr.add(Restrictions.eq("id", beerID));
			Beer beer = (Beer) cr.list().get(0);
			
			cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("id", userID));
			SavedUser user = (SavedUser) cr.list().get(0);
			
			BeerPost post = new BeerPost();
			
			post.setBar(bar);
			post.setBeer(beer);
			post.setUser(user);
			post.setDescription(descripton);
			
			// save post
			int id = (int) session.save(post);
			
			bar.getBeerPosts().add(post);
			beer.getBeerPosts().add(post);
			user.getBeerPosts().add(post);
			
			//update all other objects with post
			session.update(bar);
			session.update(beer);
			session.update(user);

			// commit
			session.getTransaction().commit();

			return id;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			
			return 0;
		} finally {
			// close session
			session.close();
		}
	}
}

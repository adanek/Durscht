package durscht.data.handler;

import durscht.contracts.data.*;
import durscht.data.model.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * communication interface between the core and the data layer of our project
 * class to handle all database interactions
 * 
 * @author Witsch Daniel, Deutsch Patrick
 *
 */
public class DataHandler implements IDataHandler {

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	private Connection connection;

	private static boolean testDB = false;

	/**
	 * Constructor for Databasehandler, it is important that only one instance
	 * of this class will be created
	 * 
	 * @throws IllegalStateException
	 *             occurs when connection not possible or URI is wrong
	 */
	public DataHandler() throws IllegalStateException {

		try {
			// connect to db
			connection = connectToDatabase();

			// create session factory
			Configuration configuration = new Configuration();
			// productive DB
			if (testDB == false) {
				configuration.configure();
				// test DB
			} else {
				configuration
						.configure("durscht/data/testConf/hibernateTest.cfg.xml");
			}
			serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (HibernateException e) {
			System.out.println("Hibernate problems");
			throw new IllegalStateException("Hibernate problems");
		} catch (URISyntaxException e) {
			System.out.println("wrong URI to database");
			throw new IllegalStateException("wrong URI to database");
		} catch (IllegalStateException e) {
			System.out.println("no connection to database");
			throw new IllegalStateException("no connection to database");
		} catch(Exception e){
			System.out.println("some connection error");
		}
	}

	// set testDB
	public static void setTestDB(boolean value) {
		testDB = value;
	}

	public void closeDatabaseConnection() throws IllegalStateException {
		sessionFactory.close();
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println("closing connection not possible");
			throw new IllegalStateException("closing connection not possible");
		}
	}

	/**
	 * connect to the database
	 * 
	 * @return the Connection to the database
	 * @throws URISyntaxException
	 *             throw this exception when the URI to the database is wrong
	 * @throws IllegalStateException
	 *             throw this exception when it is not possible to connect
	 */
	private Connection connectToDatabase() throws URISyntaxException,
			IllegalStateException {

		URI dbUri = new URI(
				"postgres://kydpvhoibhlkkv:zryvjK70cy2693A8I-TtSzXQUk@ec2-23-21-140-156.compute-1.amazonaws.com:5432/dcu5dug781g9t8");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://"
				+ dbUri.getHost()
				+ ':'
				+ dbUri.getPort()
				+ dbUri.getPath()
				+ "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		Connection conn;
		try {
			conn = DriverManager.getConnection(dbUrl, username, password);
		} catch (Exception e) {
			System.out.println("closing connection not possible");
			throw new IllegalStateException("closing connection not possible");
		}

		return conn;
	}

	/**
	 * open a new Session
	 * 
	 * @return the session
	 */
	private Session openSession() {

		return sessionFactory.openSession();
	}

	/**
	 * save an object to the database, when it is an entity
	 * 
	 * @param the
	 *            object of an entity
	 * @return the ID of the entity
	 * @throws IllegalStateException
	 *             commit failed by saving from object
	 */
	private Integer saveObjectToDb(Object obj) throws IllegalStateException {

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
			System.out.println("saving from object not possible");
			throw new IllegalStateException("saving from object not possible",
					e);
		} finally {
			// close session
			session.close();
		}

	}

	/**
	 * get an Object from the Database with the id
	 * 
	 * @param id
	 * @param typeParameterClass
	 *            Class type of the searched class for example (Bar.class)
	 * @return Object with the corresponding id
	 * @throws IllegalStateException
	 *             commit failed by searching for object, no object with this ID
	 *             in the database
	 */
	private <T> T searchForID(int id, Class<T> typeParameterClass)
			throws IllegalArgumentException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(typeParameterClass);
			cr.add(Restrictions.eq("id", id));
			List<T> results = cr.list();

			// commit
			session.getTransaction().commit();

			//if the object is a user, load its achievements
			if(results.size() > 0){
				if(results.get(0).getClass().equals(SavedUser.class)){
					SavedUser user = (SavedUser) results.get(0);
					Hibernate.initialize(user.getAchievements());
					return (T) user;
				}
			}
			
			// only one element in the list because the id is unique
			return results.get(0);

		} catch (IndexOutOfBoundsException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("object with this ID is not in the database");
			throw new IllegalArgumentException(
					"object with this ID is not in the database", e);
		} finally {
			// close session
			session.close();
		}
	}

	public IUser createUser(String name, String email, String password,
			boolean admin) throws IllegalStateException {

		// create user instance
		SavedUser user = new SavedUser();
		user.setEmail(email);
		user.setJoinedDate(new Date());
		user.setName(name);
		user.setAdmin(admin);
		try {
			user.setPassword(PasswordHash.getSaltedHash(password));
		} catch (Exception e) {
			System.out.println("Creation of Hash failed");
			throw new IllegalStateException("Creation of Hash failed", e);
		}

		// save user in database
		saveObjectToDb(user);
		return user;
	}

	public IBeer createBeer(String brand, String type, String description,
			boolean verified) throws IllegalStateException {

		// create beer instance
		Beer beer = new Beer();
		beer.setBrand(brand);
		beer.setType(type);
		beer.setDescription(description);
		beer.setVerified(verified);

		// save beer to database
		saveObjectToDb(beer);
		return beer;
	}

	public IBar createBar(String name, double latitude, double longitude,
			String description, String url) throws IllegalStateException {

		// create bar instance
		Bar bar = new Bar();
		bar.setName(name);
		bar.setLatitude(latitude);
		bar.setLongitude(longitude);
		bar.setDescription(description);
		bar.setUrl(url);

		// save bar to database
		saveObjectToDb(bar);
		return bar;
	}

	public IAchievement createAchievement(String name, String description)
			throws IllegalStateException {

		// create achievement instance
		Achievement ach = new Achievement();
		ach.setName(name);
		ach.setDescription(description);

		// save bar to database
		saveObjectToDb(ach);
		return ach;
	}

	public IAchievementCriterion createAchievementCriterion(
			AchievementCriterionType type, int value)
			throws IllegalStateException {

		// create user instance
		AchievementCriterion criterion = new AchievementCriterion();
		criterion.setType(type);
		criterion.setValue(value);

		// save user in database
		saveObjectToDb(criterion);
		return criterion;
	}

	public IBeerPost createPost(int barID, int beerID, int userID,
			double price, int rating, String descripton)
			throws IllegalStateException, IllegalArgumentException {
		Session session = openSession();
		Beer beer;
		Bar bar;
		SavedUser user;

		// begin transaction
		session.beginTransaction();

		// search bar
		try {
			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			bar = (Bar) cr.list().get(0);
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("barID in database not found");
			// close session
			session.close();
			throw new IllegalArgumentException("barID: not in database found",
					e);
		}

		// search beer
		try {
			Criteria cr = session.createCriteria(Beer.class);
			cr.add(Restrictions.eq("id", beerID));
			beer = (Beer) cr.list().get(0);
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("beerID in database not found");
			// close session
			session.close();
			throw new IllegalArgumentException("beerID: not in database found",
					e);
		}

		// search user
		try {
			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("id", userID));
			user = (SavedUser) cr.list().get(0);
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("userID in database not found");
			// close session
			session.close();
			throw new IllegalArgumentException("userID: not in database found",
					e);
		}

		// create new post and save this to database
		try {
			BeerPost post = new BeerPost();

			post.setBar(bar);
			post.setBeer(beer);
			post.setUser(user);
			post.setPrice(price);
			post.setRating(rating);
			post.setDescription(descripton);
			post.setTimeDate(new Date());

			// save post
			session.save(post);

			bar.getBeerPosts().add(post);
			beer.getBeerPosts().add(post);
			user.getBeerPosts().add(post);

			// update all other objects with post
			session.update(bar);
			session.update(beer);
			session.update(user);

			// commit
			session.getTransaction().commit();

			return post;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("saving from post failed");
			throw new IllegalStateException("saving from post failed", e);
		} finally {
			// close session
			session.close();
		}
	}

	/**
	 * deletes an object from the database, when it is an entity
	 * 
	 * @param obj
	 *            the object of an entity
	 * @throws IllegalArgumentException
	 *             deletion of object failed
	 */
	private void deleteObjectFromDb(Object obj) throws IllegalArgumentException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// save an object
			session.delete(obj);

			// commit
			session.getTransaction().commit();

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			// deletion failed
			System.out.println("deletion of object failed");
			throw new IllegalArgumentException("deletion of object failed", e);
		} finally {
			// close session
			session.close();
		}
	}

	public void deleteUser(int userID) throws IllegalArgumentException {
		try {
			// get user
			SavedUser user = getUserByID(userID);

			//first delete the achievements
			if (user.getAchievements().size() > 0) {
				try{
				deleteAchievementsFromUser(user);
				//exception weitergeben
				} catch(Exception e){
					throw e;
				}
			}
			
			// delete user from database
			deleteObjectFromDb(user);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting user from ID failed");
			throw new IllegalArgumentException(
					"deletion or getting user from ID failed", e);
		}
	}

	private void deleteAchievementsFromUser(SavedUser user) throws IllegalArgumentException {

		//reset achievements
		user.setAchievements(null);

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// save an object
			session.update(user);

			// commit
			session.getTransaction().commit();

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			// deletion failed
			System.out.println("deletion of object failed");
			throw new IllegalArgumentException(
					"deletion of object failed", e);
		} finally {
			// close session
			session.close();
		}
	}

	public void deleteBeer(int beerID) throws IllegalArgumentException {
		try {
			// get beer
			Beer beer = getBeerByID(beerID);
			// delete beer from database
			deleteObjectFromDb(beer);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting beer from ID failed");
			throw new IllegalArgumentException(
					"deletion or getting beer from ID failed", e);
		}
	}

	public void deleteBar(int barID) throws IllegalArgumentException {
		try {
			// get bar
			Bar bar = getBarByID(barID);
			// delete bar from database
			deleteObjectFromDb(bar);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting bar from ID failed");
			throw new IllegalArgumentException(
					"deletion or getting bar from ID failed", e);
		}
	}

	/**
	 * deletes a achievement from the database
	 * 
	 * @param aID
	 * @throws IllegalArgumentException
	 *             deletion or getting achievement from ID failed
	 */
	@Deprecated
	public void deleteAchievement(int aID) throws IllegalArgumentException {
		try {
			// get achievement
			Achievement achievement = getAchievementByID(aID);
			// delete beer from database
			deleteObjectFromDb(achievement);
		} catch (IllegalArgumentException e) {
			System.out
					.println("deletion or getting achievement from ID failed");
			throw new IllegalArgumentException(
					"deletion or getting achievement from ID failed", e);
		}
	}

	public void deletePost(int postID) throws IllegalArgumentException {
		try {
			// get post
			BeerPost post = getPostByID(postID);
			// delete post from database
			deleteObjectFromDb(post);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting post from ID failed");
			throw new IllegalArgumentException(
					"deletion or getting post from ID failed", e);
		}
	}

	public IBeer verifyBeer(int beerID) throws IllegalArgumentException,
			IllegalStateException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// get beer
			Criteria cr = session.createCriteria(Beer.class);
			cr.add(Restrictions.eq("id", beerID));
			List<Beer> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException("beerID");

			Beer beer = results.get(0);

			// verify beer
			beer.setVerified(true);

			// update beer
			session.update(beer);

			// commit
			session.getTransaction().commit();

			return beer;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no beer with this ID in the database");
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("verifieng beer");
			throw new IllegalStateException("verifieng beer");
		} finally {
			// close session
			session.close();
		}

	}

	public IAchievement assignCriterionToAchievement(int achID, int critID)
			throws IllegalArgumentException, IllegalStateException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// get achievement
			Criteria cr = session.createCriteria(Achievement.class);
			cr.add(Restrictions.eq("id", achID));
			List<Achievement> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException("achID");

			// get achievment
			Achievement ach = results.get(0);

			// get criterion
			cr = session.createCriteria(AchievementCriterion.class);
			cr.add(Restrictions.eq("id", critID));
			List<AchievementCriterion> critResults = cr.list();

			if (critResults.size() == 0)
				throw new IllegalArgumentException("achID");

			AchievementCriterion newCrit = critResults.get(0);

			// criterion already in list from achievement
			if (ach.getCriterion().contains(newCrit))
				throw new IllegalArgumentException(
						"achievement has already this criterion");

			// add criterion to achievement
			ach.getCriterion().add(newCrit);

			// update user and achievement
			session.update(ach);

			// commit
			session.getTransaction().commit();

			return ach;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("adding criterion to achievement failed");
			throw new IllegalStateException(
					"adding criterion to achievement failed");
		} finally {
			// close session
			session.close();
		}
	}

	public IUser assignAchievementToUser(int userID, int achID)
			throws IllegalArgumentException, IllegalStateException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// get user
			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("id", userID));
			List<SavedUser> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException("userID");

			// get user
			SavedUser user = results.get(0);

			// get achievement
			cr = session.createCriteria(Achievement.class);
			cr.add(Restrictions.eq("id", achID));
			List<Achievement> achResults = cr.list();

			if (achResults.size() == 0)
				throw new IllegalArgumentException("achID");

			Achievement newAch = achResults.get(0);

			// achievement already in list from user
			if (user.getAchievements().contains(newAch))
				throw new IllegalArgumentException(
						"user has this achievement already");

			// add achievement to users achievements and add user to achievement
			user.getAchievements().add(newAch);
			newAch.getUsers().add(user);

			// update user and achievement
			session.update(user);
			session.update(newAch);

			// commit
			session.getTransaction().commit();

			return user;

		} catch (IllegalArgumentException e) { // Exception -> rollback
			session.getTransaction().rollback();
			System.out
					.println("no user with this ID or no achievement with this ID in the database");
			throw new IllegalArgumentException(e.getMessage());
		} catch (Exception e) { // Exception -> rollback
								// session.getTransaction().rollback();
			System.out.println("saving from achievement");
			throw new IllegalStateException("saving from achievement");
		} finally { // close session
			session.close();
		}
	}

	public Collection<IUser> getAllUsers() throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			List<IUser> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the user list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeer> getAllBeers() throws IllegalStateException {
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
			throw new IllegalStateException(
					"something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeer> getAllBeersVerified() throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Beer.class);
			cr.add(Restrictions.eq("verified", true));
			List<IBeer> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeer> getAllBeersUnverified()
			throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Beer.class);
			cr.add(Restrictions.eq("verified", false));
			List<IBeer> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBar> getAllBars() throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			List<IBar> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the bar list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IAchievement> getAllAchievements()
			throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Achievement.class);
			List<IAchievement> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the achievement list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeerPost> getAllPosts() throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(BeerPost.class);
			List<IBeerPost> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the post list");
		} finally {
			// close session
			session.close();
		}
	}

	public SavedUser getUserByID(int id) throws IllegalArgumentException {
		return this.<SavedUser> searchForID(id, SavedUser.class);
	}

	public Beer getBeerByID(int id) throws IllegalArgumentException {
		return this.<Beer> searchForID(id, Beer.class);
	}

	public Bar getBarByID(int id) throws IllegalArgumentException {
		return this.<Bar> searchForID(id, Bar.class);
	}

	public Achievement getAchievementByID(int id)
			throws IllegalArgumentException {
		return this.<Achievement> searchForID(id, Achievement.class);
	}

	public BeerPost getPostByID(int id) throws IllegalArgumentException {
		return this.<BeerPost> searchForID(id, BeerPost.class);
	}

	public IUser getUserLogin(String name, String password)
			throws IllegalStateException {
		Session session = openSession();

		System.out.println("getUserLogin started");
		
		System.out.println("User: "+ name);
		System.out.println("Password: " + password);
		
		try {

			// begin transaction
			session.beginTransaction();

			System.out.println("Transaction started");
			
			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("name", name));
			List<SavedUser> results = cr.list();

			// commit
			session.getTransaction().commit();

			System.out.println("Transaction committed");
			
			// only one element in the list because the id is unique
			for (SavedUser user : results) {
				try {
					System.out.println("PW check started");
					if (PasswordHash.check(password, user.getPassword()))
						System.out.println("Return user");
						return user;
				} catch (Exception e) {
					System.out.println("PW check failed");
					throw new IllegalStateException("Fail by checking the user password");
				}
			}
			System.out.println("no users found");
		} catch (HibernateException e) {
			// Exception -> rollback
			System.out.println("Error!!");
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the user");
		} finally {
			// close session
			session.close();
		}
		// no appropriate user found in database
		return null;
	}

	public IUser getUserLoginAdmin(String name, String password)
			throws IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("name", name));
			cr.add(Restrictions.eq("admin", true));
			List<SavedUser> results = cr.list();

			// commit
			session.getTransaction().commit();

			// only one element in the list because the id is unique
			for (SavedUser user : results) {
				try {
					if (PasswordHash.check(password, user.getPassword()))
						return user;
				} catch (Exception e) {
					throw new IllegalStateException(
							"Fail by checking the user password");
				}
			}
		} catch (HibernateException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the user");
		} finally {
			// close session
			session.close();
		}
		// no appropriate user found in database
		return null;
	}

	public Collection<IBar> getBarsCoordinates(double fromLatitude,
			double toLatitude, double fromLongitude, double toLongitude) {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.le("latitude", toLatitude));
			cr.add(Restrictions.ge("latitude", fromLatitude));
			cr.add(Restrictions.le("longitude", toLongitude));
			cr.add(Restrictions.ge("longitude", fromLongitude));
			List<IBar> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the bar list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IAchievement> getAllAchievementsFromUser(int userID)
			throws IllegalArgumentException, IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("id", userID));
			List<SavedUser> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException();
			// user not found with this id

			Collection<Achievement> achievements = results.get(0)
					.getAchievements();

			Collection<IAchievement> ret = new ArrayList<>(achievements);

			// commit
			session.getTransaction().commit();

			return ret;

		} catch (IllegalArgumentException e) { // Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no user with this ID in the database");
			throw new IllegalArgumentException(
					"no user with this ID in the database");
		} catch (Exception e) { // Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the achievement list");
		} finally { // close session
			session.close();
		}
	}

	public Collection<IBeerPost> getAllPostsFromUser(int userID)
			throws IllegalArgumentException, IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("id", userID));
			List<SavedUser> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException(); // user not found with
														// this id

			Collection<BeerPost> posts = results.get(0).getBeerPosts();

			Collection<IBeerPost> ret = new ArrayList<>(posts);

			// commit
			session.getTransaction().commit();

			return ret;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no user with this ID in the database");
			throw new IllegalArgumentException(
					"no user with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the post list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeer> getAllBeersFromBar(int barID)
			throws IllegalArgumentException, IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			List<Bar> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException(); // bar not found with this
														// id

			Collection<BeerPost> posts = results.get(0).getBeerPosts();
			Collection<IBeer> beers = new ArrayList<>();

			for (BeerPost post : posts) {
				if (!beers.contains(post.getBeer())) {
					beers.add(post.getBeer());
				}
			}

			// commit
			session.getTransaction().commit();

			return beers;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no beer with this ID in the database");
			throw new IllegalArgumentException(
					"no beer with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBeerPost> getAllPostsFromBar(int barID)
			throws IllegalArgumentException, IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);
			cr.add(Restrictions.eq("id", barID));
			List<Bar> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException(); // bar not found with this
														// id

			Collection<BeerPost> posts = results.get(0).getBeerPosts();

			Collection<IBeerPost> ret = new ArrayList<>(posts);

			// commit
			session.getTransaction().commit();

			return ret;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no beer with this ID in the database");
			throw new IllegalArgumentException(
					"no beer with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the post list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IAchievementCriterion> getAllCriterionFromAchievement(
			int achID) throws IllegalArgumentException, IllegalStateException {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Achievement.class);
			cr.add(Restrictions.eq("id", achID));
			List<Achievement> results = cr.list();

			if (results.size() == 0)
				throw new IllegalArgumentException(); // achievement not found
														// with
														// this id

			Collection<AchievementCriterion> crit = results.get(0)
					.getCriterion();

			Collection<IAchievementCriterion> ret = new ArrayList<>(crit);

			// commit
			session.getTransaction().commit();

			return ret;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no achievement with this ID in the database");
			throw new IllegalArgumentException(
					"no achievement with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the criterion list");
		} finally {
			// close session
			session.close();
		}
	}

	public Collection<IBar> findBars(double fromLatitude, double toLatitude,
			double fromLongitude, double toLongitude,
			Collection<Integer> beerIDs) throws IllegalStateException {

		Collection<IBar> bars = getBarsCoordinates(fromLatitude, toLatitude,
				fromLongitude, toLongitude);

		// fetching all beers
		Collection<IBeer> beers = new ArrayList<>();
		for (Integer id : beerIDs) {
			beers.add(getBeerByID(id));
		}

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(BeerPost.class);

			cr.add(Restrictions.in("beer", beers));
			cr.add(Restrictions.in("bar", bars));
			List<BeerPost> results = cr.list();

			Collection<IBar> ret = new ArrayList<>();

			for (BeerPost post : results) {
				if (!ret.contains(post.getBar()))
					ret.add(post.getBar());
			}

			// commit
			session.getTransaction().commit();

			return ret;
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			throw new IllegalStateException(
					"something went wrong by getting the bar list");
		} finally {
			// close session
			session.close();
		}

	}
}

package durscht.data.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.data.IUser;
import durscht.data.model.Achievement;
import durscht.data.model.Bar;
import durscht.data.model.Beer;
import durscht.data.model.BeerPost;
import durscht.data.model.SavedUser;

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
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
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
		}
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
	private Connection connectToDatabase() throws URISyntaxException, IllegalStateException {

		URI dbUri = new URI(
				"postgres://kydpvhoibhlkkv:zryvjK70cy2693A8I-TtSzXQUk@ec2-23-21-140-156.compute-1.amazonaws.com:5432/dcu5dug781g9t8");

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort()
				+ dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

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
			throw new IllegalStateException("saving from object not possible", e);
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
	 *             commit failed by searching for object
	 */
	private <T> T searchForID(int id, Class<T> typeParameterClass) throws IllegalArgumentException {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(typeParameterClass);
			cr.add(Restrictions.eq("id", id));
			List<T> results = cr.list();

			// commit
			session.getTransaction().commit();

			// only one element in the list because the id is unique
			return results.get(0);

		} catch (IndexOutOfBoundsException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("object with this ID is not in the database");
			throw new IllegalArgumentException("object with this ID is not in the database", e);
		} finally {
			// close session
			session.close();
		}
	}

	public IUser createUser(String name, String email, String password)
			throws IllegalStateException {

		// create user instance
		SavedUser user = new SavedUser();
		user.setEmail(email);
		user.setJoinedDate(new Date());
		user.setName(name);
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

	public IBeer createBeer(String name, String description) throws IllegalStateException {

		// create beer instance
		Beer beer = new Beer();
		beer.setName(name);
		beer.setDescription(description);

		// save beer to database
		saveObjectToDb(beer);
		return beer;
	}

	public IBar createBar(String name, double latitude, double longitude, String description,
			String url) throws IllegalStateException {

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

		// save achievement to database
		saveObjectToDb(ach);
		return ach;
	}

	public IBeerPost createPost(int barID, int beerID, int userID, double price, int rating,
			String descripton) throws IllegalStateException, IllegalArgumentException {
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
			throw new IllegalArgumentException("barID: not in database found", e);
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
			throw new IllegalArgumentException("beerID: not in database found", e);
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
			throw new IllegalArgumentException("userID: not in database found", e);
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

	public IUser getUserLogin(String name, String password) {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(SavedUser.class);
			cr.add(Restrictions.eq("name", name));
			List<SavedUser> results = cr.list();

			// commit
			session.getTransaction().commit();

			// only one element in the list because the id is unique
			for (SavedUser user : results) {
				try {
					if (PasswordHash.check(password, user.getPassword()))
						return user;
				} catch (Exception e) {
					System.out.println("Fail by checking the user password");
				}
			}
		} catch (HibernateException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("something went wrong with database by getting user");
		} finally {
			// close session
			session.close();
		}
		return null;
	}

	public SavedUser getUserByID(int id) throws IllegalArgumentException {
		return this.<SavedUser> searchForID(id, SavedUser.class);
	}

	public Bar getBarByID(int id) throws IllegalArgumentException {
		return this.<Bar> searchForID(id, Bar.class);
	}

	public Beer getBeerByID(int id) throws IllegalArgumentException {
		return this.<Beer> searchForID(id, Beer.class);
	}

	public BeerPost getPostByID(int id) throws IllegalArgumentException {
		return this.<BeerPost> searchForID(id, BeerPost.class);
	}

	public Achievement getAchievementByID(int id) throws IllegalArgumentException {
		return this.<Achievement> searchForID(id, Achievement.class);
	}

	public Collection<IBeer> getAllBeers() {
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
			System.out.println("something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
		// when exception happened
		return null;
	}

	public Collection<IBar> getBarsCoordinates(double fromLatitude, double toLatitude,
			double fromLongitude, double toLongitude) {
		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			Criteria cr = session.createCriteria(Bar.class);

			// temporäre Lösung -> soll auf le und ge umgebaut werden
			cr.add(Restrictions.between("latitude", (fromLatitude - 0.0000000000001),
					(toLatitude + 0.0000000000001)));
			cr.add(Restrictions.between("longitude", (fromLongitude - 0.0000000000001),
					(toLongitude + 0.0000000000001)));
			List<IBar> results = cr.list();

			// commit
			session.getTransaction().commit();

			return results;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("something went wrong by getting the bar list");
		} finally {
			// close session
			session.close();
		}
		return null;
	}

	public Collection<IBeer> getAllBeersFromBar(int barID) throws IllegalArgumentException {
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
				beers.add(post.getBeer());
			}

			// commit
			session.getTransaction().commit();

			return beers;

		} catch (IllegalArgumentException e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("no beer with this ID in the database");
			throw new IllegalArgumentException("no beer with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("something went wrong by getting the beer list");
		} finally {
			// close session
			session.close();
		}
		return null;
	}

	public Collection<IBeerPost> getAllPostsFromBar(int barID) throws IllegalArgumentException {
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
			throw new IllegalArgumentException("no beer with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("something went wrong by getting the post list");
		} finally {
			// close session
			session.close();
		}
		return null;
	}

	public Collection<IBeerPost> getAllPostsFromUser(int userID) throws IllegalArgumentException {
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
			throw new IllegalArgumentException("no user with this ID in the database");
		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();
			System.out.println("something went wrong by getting the post list");
		} finally {
			// close session
			session.close();
		}
		return null;
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

	public void deleteBar(int barID) throws IllegalArgumentException {
		try {
			// get bar
			Bar bar = getBarByID(barID);
			// delete bar from database
			deleteObjectFromDb(bar);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting bar from ID failed");
			throw new IllegalArgumentException("deletion or getting bar from ID failed", e);
		}
	}

	public void deleteUser(int userID) throws IllegalArgumentException {
		try {
			// get user
			SavedUser user = getUserByID(userID);
			// delete user from database
			deleteObjectFromDb(user);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting user from ID failed");
			throw new IllegalArgumentException("deletion or getting user from ID failed", e);
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
			throw new IllegalArgumentException("deletion or getting beer from ID failed", e);
		}
	}

	/*
	 * public void deletePost(int postID) throws IllegalArgumentException { try
	 * { // get post BeerPost post = getPostByID(postID); // delete post from
	 * database deleteObjectFromDb(post); } catch (IllegalArgumentException e) {
	 * System.out.println("deletion or getting post from ID failed"); throw new
	 * IllegalArgumentException("deletion or getting post from ID failed", e); }
	 * }
	 */

	public void deleteAchievement(int aID) throws IllegalArgumentException {
		try {
			// get achievement
			Achievement achievement = getAchievementByID(aID);
			// delete beer from database
			deleteObjectFromDb(achievement);
		} catch (IllegalArgumentException e) {
			System.out.println("deletion or getting achievement from ID failed");
			throw new IllegalArgumentException("deletion or getting achievement from ID failed", e);
		}
	}
}

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import durscht.contracts.IAchievement;
import durscht.contracts.IBar;
import durscht.contracts.IBeer;
import durscht.contracts.IDataHandler;
import durscht.contracts.IUser;
import durscht.model.Achievement;
import durscht.model.Bar;
import durscht.model.Beer;
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

	@Override
	public Integer createUser(String name, String email, String password) {

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

	@Override
	public Integer createBeer(String name, String description) {

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
	public Integer createBar(String name, double latitude, double longitude, String description, String url){
		
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
	public Integer createAchievement(String name, String description){
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
	 * search for a bar by ID
	 * @return Bar or null when no user exists in the database with this ID
	 */
	public IBar getBarByID(int id){
		return this.<Bar>searchForID(id, Bar.class);
	}
	
	/**
	 * search for a beer by ID
	 * @return Beer or null when no user exists in the database with this ID
	 */
	public IBeer getBeerByID(int id){
		return this.<Beer>searchForID(id, Beer.class);
	}
	
	/**
	 * search for a achievement by ID
	 * @return Achievement or null when no user exists in the database with this ID
	 */
	public IAchievement getAchievementByID(int id){
		return this.<Achievement>searchForID(id, Achievement.class);
	}

}

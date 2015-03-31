import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.IDataHandler;
import durscht.model.Beer;
import durscht.model.SavedUser;

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

	@Override
	public void createUser(String name, String email, String password) {

		// create user instance
		SavedUser user = new SavedUser();
		user.setEmail(email);
		user.setJoinedDate(new Date());
		user.setName(name);

		// password hash to be implemented

		// save user in database
		saveObjectToDb(user);

	}

	// open a new session
	private Session openSession() {

		return sessionFactory.openSession();
	}

	// save a new object in the database
	private boolean saveObjectToDb(Object obj) {

		Session session = openSession();

		try {

			// begin transaction
			session.beginTransaction();

			// save an object
			session.save(obj);

			// commit
			session.getTransaction().commit();

			return true;

		} catch (Exception e) {
			// Exception -> rollback
			session.getTransaction().rollback();

			return false;
		} finally {
			// close session
			session.close();
		}

	}

	@Override
	public void createBeer(String name, String description) {

		//create beer instance
		Beer beer = new Beer();
		beer.setName(name);
		beer.setDescription(description);
		
		//save beer to database
		saveObjectToDb(beer);

	}

}

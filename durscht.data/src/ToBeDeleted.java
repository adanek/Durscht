import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

public class ToBeDeleted {

	public static void main(String args[]) {

		try {
			Connection testConn = getConnection();

			SavedUser user1 = new SavedUser();
			user1.setEmail("daniel.witsch@gmx.at");
			user1.setJoinedDate(new Date());
			user1.setName("Daniel");
			SavedUser user2 = new SavedUser();
			user2.setEmail("patrick.deutsch@gmx.at");
			user2.setJoinedDate(new Date());
			user2.setName("Patrick");

			Bar bar1 = new Bar();
			bar1.setName("Gössers");
			Bar bar2 = new Bar();
			bar2.setName("Stiegl Bräu");

			Beer beer1 = new Beer();
			beer1.setName("Stiegl");
			Beer beer2 = new Beer();
			beer2.setName("Starkenberger");

			BeerPost post1 = new BeerPost();
			post1.setUser(user1);
			post1.setBar(bar1);
			post1.setBeer(beer1);
			BeerPost post2 = new BeerPost();
			post2.setUser(user2);
			post2.setBar(bar2);
			post2.setBeer(beer2);

			BeerPost post3 = new BeerPost();
			post3.setUser(user2);
			post3.setBar(bar1);
			post3.setBeer(beer2);

			user1.getBeerPosts().add(post1);
			user2.getBeerPosts().add(post2);
			user2.getBeerPosts().add(post3);
			bar1.getBeerPosts().add(post1);
			bar2.getBeerPosts().add(post2);
			bar1.getBeerPosts().add(post3);
			beer1.getBeerPosts().add(post1);
			beer2.getBeerPosts().add(post2);
			beer2.getBeerPosts().add(post3);

			// create session factory
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();

			// create session
			Session session = sessionFactory.openSession();

			try {

				// begin transaction
				session.beginTransaction();

				// save an object
				session.save(user1);
				session.save(user2);
				session.save(bar1);
				session.save(bar2);
				session.save(beer1);
				session.save(beer2);
				session.save(post1);
				session.save(post2);
				session.save(post3);

				// commit
				session.getTransaction().commit();

			} catch (Exception e) {
				// Exception -> rollback
				session.getTransaction().rollback();
			} finally {
				// close session
				session.close();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Connection getConnection() throws URISyntaxException,
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

}

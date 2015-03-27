import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.model.User;

public class ToBeDeleted {

	public static void main(String args[]){
		
		try {
			Connection testConn = getConnection();
			
			User user = new User();
			
			user.setFirstname("Pati");
			user.setLastname("Deutsch");
			user.setAddress("Uni");
			user.setJoinedDate(new Date());
			user.setDescription("Uni");
			
			// create session factory
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();

			// create session
			Session session = sessionFactory.openSession();

			try {

				// begin transaction
				session.beginTransaction();

				// save an object
				session.save(user);

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
	
	private static Connection getConnection() throws URISyntaxException, SQLException {
	    
		URI dbUri = new URI("postgres://lncxojfprcynxw:YqnYBdYl9e5JweBKBFIIP3n48M@ec2-54-163-228-58.compute-1.amazonaws.com:5432/d5nj2e3kege64a");

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
}

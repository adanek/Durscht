import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.data.IUser;
import durscht.handler.DataHandler;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.BeerPost;
import durscht.model.SavedUser;

public class ToBeDeleted {

	public static void main(String args[]) {

			//get handler
			IDataHandler handler  = new DataHandler();
			
			//create new users
			int userid1 = handler.createUser("admin", "admin@gmx.at", "admin");
			int userid2 = handler.createUser("user2", "user2@gmx.at", "user2");
			int userid3 = handler.createUser("user3", "user3@gmx.at", "user3");

			//create new beers
			int beerid1 = handler.createBeer("Gösser", "Herbes österreichisches Bier");
			int beerid2 = handler.createBeer("Stiegl", "Originales traditionelles Salzburger Bier");
			int beerid3 = handler.createBeer("Ottakringer", "Traditionelles Wiener Bier");
			int beerid4 = handler.createBeer("Starkenberger", "Heimisches Bier aus dem Schloss Starkenberg in Tarrenz");
			
			//create new Bars
			int barid1 = handler.createBar("Theresien Bräu", 47.263835, 11.394931, "Traditionelles Bierbrauerei aus Innsbruck, das ein sehr gutes Restaurant ist und auch zum Ausgehen gut geeignet ist.", "www.brauwirtshaus.at");
			int barid2 = handler.createBar("11er Haus", 47.268653, 11.392825, "Sehr bekannte Bar in Innsbruck, die sehr viele Biersorten der ganzen Welt anbietet.", "http://innsbruckplus.at/elferhaus/");
			int barid3 = handler.createBar("Stadtcafe", 47.268785, 11.395963, "Bekanntestes Nachtlokal in Innsbruck, das hauptsächlich von der Studentenszene besucht wird.", "www.tagnacht.at/stadtcafe/");
			
			//create new achievement
			int achid1 = handler.createAchievement("10 posts", "The user will get this achievement when he drinks 10 beers and add a post to the app");
			int achid2 = handler.createAchievement("50 posts", "The user will get this achievement when he drinks 50 beers and add a post to the app");
			int achid3 = handler.createAchievement("100 posts", "The user will get this achievement when he drinks 100 beers and add a post to the app");
			
			IUser user = handler.getUserLogin("user2", "user2");
			
			handler.closeDatabaseConnection();
	}

}

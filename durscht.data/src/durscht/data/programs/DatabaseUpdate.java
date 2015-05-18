package durscht.data.programs;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import durscht.contracts.data.AchievementCriterionType;
import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IAchievementCriterion;
import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.data.IUser;
import durscht.data.handler.DataHandler;
import durscht.data.model.Bar;
import durscht.data.model.Beer;
import durscht.data.model.BeerPost;
import durscht.data.model.SavedUser;

public class DatabaseUpdate {

	public static void main(String args[]) {

		try {

			DataHandler.setTestDB(true);
			// get handler
			IDataHandler handler = new DataHandler();
<<<<<<< HEAD

=======
			
>>>>>>> origin/db
			// create new users
			int userid1 = handler.createUser("admin", "admin@gmx.at", "admin", true).getId();
			int userid2 = handler.createUser("user2", "user2@gmx.at", "user2", false).getId();
			int userid3 = handler.createUser("user3", "user3@gmx.at", "user3", false).getId();

			//create achievement critera
			int cid1 = handler.createAchievementCriterion(AchievementCriterionType.TOTAL_NO_BEERS, 10).getId();
			int cid2 = handler.createAchievementCriterion(AchievementCriterionType.CRIT2, 10).getId();
			int cid3 = handler.createAchievementCriterion(AchievementCriterionType.CRIT3, 10).getId();
			
			// create new beers
			int beerid1 = handler.createBeer("Gösser", "Radler", "Herbes österreichisches Bier",
					false).getId();
			int beerid2 = handler.createBeer("Stiegl", "Goldlager",
					"Originales traditionelles Salzburger Bier", true).getId();
			int beerid3 = handler.createBeer("Ottakringer", "Helles", "Traditionelles Wiener Bier",
					false).getId();
			int beerid4 = handler.createBeer("Starkenberger", "Märzen",
					"Heimisches Bier aus dem Schloss Starkenberg in Tarrenz", true).getId();

			// create new Bars
			int barid1 = handler.createBar("Theresien Bräu", 47.269258, 11.4040792,
<<<<<<< HEAD
					"Traditionelles Lokal in Innsbruck, das ein eigenes Bier braut",
					"www.brauwirtshaus.at").getId();
=======
					"Traditionelles Lokal in Innsbruck, das ein eigenes Bier braut", "www.brauwirtshaus.at", true).getId();
>>>>>>> origin/db
			int barid2 = handler
					.createBar(
							"11er Haus",
							47.268653,
							11.392825,
							"Sehr bekannte Bar in Innsbruck, die sehr viele Biersorten der ganzen Welt anbietet.",
							"http://innsbruckplus.at/elferhaus/", true).getId();
			int barid3 = handler
					.createBar(
							"Stadtcafe",
							47.268785,
							11.395963,
							"Bekanntestes Nachtlokal in Innsbruck, das hauptsächlich von der Studentenszene besucht wird.",
							"www.tagnacht.at/stadtcafe/", true).getId();

			// create new achievement
			int achid1 = handler
					.createAchievement("500 posts",
							"The user will get this achievement when he drinks 500 beers and add a post to the app", cid1)
					.getId();
			int achid2 = handler
					.createAchievement("50 posts",
							"The user will get this achievement when he drinks 50 beers and add a post to the app", cid3)
					.getId();
			int achid3 = handler
					.createAchievement("100 posts",
							"The user will get this achievement when he drinks 100 beers and add a post to the app", cid3)
					.getId();

			// create posts
			int post1 = handler.createPost(barid1, beerid1, userid1, 3.0, 2, "Post1").getId();
			int post2 = handler.createPost(barid1, beerid2, userid2, 3.0, 2, "Post2").getId();
			int post3 = handler.createPost(barid2, beerid3, userid1, 3.0, 2, "Post3").getId();
			int post4 = handler.createPost(barid2, beerid4, userid3, 3.0, 2, "Post4").getId();
			int post5 = handler.createPost(barid2, beerid3, userid2, 3.0, 2, "Post5").getId();
<<<<<<< HEAD

			// test user login
			/*
			 * IUser testuser1 = handler.getUserLogin("admin", "admin"); IUser
			 * testuser2 = handler.getUserLogin("user2", "user2"); IUser
			 * testuser3 = handler.getUserLoginAdmin("admin", "admin"); IUser
			 * testuser4 = handler.getUserLoginAdmin("user3", "user3");
			 */

			// test get all beers and verify method
			/*
			 * Collection<IBeer> testbeers1 = handler.getAllBeers();
			 * Collection<IBeer> testbeers2 = handler.getAllBeersVerified();
			 * handler.verifyBeer(beerid1); handler.verifyBeer(beerid3);
			 * Collection<IBeer> testbeers3 = handler.getAllBeers();
			 * Collection<IBeer> testbeers4 = handler.getAllBeersVerified();
			 */

			// test getAll methods from model class
			/*
			 * Collection<IUser> testusers1 = handler.getAllUsers();
			 * Collection<IBar> testbars1 = handler.getAllBars();
			 * Collection<IAchievement> testachievements1 =
			 * handler.getAllAchievements(); Collection<IBeerPost> testposts1 =
			 * handler.getAllPosts();
			 */

			// test achievements
			handler.assignAchievementToUser(userid1, achid1);
			handler.assignAchievementToUser(userid1, achid3);
			handler.assignAchievementToUser(userid2, achid1);
			handler.assignAchievementToUser(userid2, achid2);
			Collection<IAchievement> testachievements2 = handler
					.getAllAchievementsFromUser(userid1);

			// test getBarsfromCoordinates
			// Collection<IBar> testbars2 = handler.getBarsCoordinates(47.2687,
			// 48, 11.39, 12); //sollte nur TheresienBräu und Stadtcafe liefern

			// test all other get methods
			/*
			 * Collection<IBeerPost> testposts2 =
			 * handler.getAllPostsFromBar(barid1); //sollte Post1,2 liefern
			 * Collection<IBeerPost> testposts3 =
			 * handler.getAllPostsFromBar(barid2); //sollte Post3,4,5 liefern
			 * Collection<IBeerPost> testposts4 =
			 * handler.getAllPostsFromUser(userid1); //sollte Post1,3 liefern
			 * Collection<IBeerPost> testposts5 =
			 * handler.getAllPostsFromUser(userid2); //sollte Post2,5 liefern
			 * Collection<IBeer> testbeers5 =
			 * handler.getAllBeersFromBar(barid1); //sollte Gösser und Stiegl
			 * liefern Collection<IBeer> testbeers6 =
			 * handler.getAllBeersFromBar(barid2); //sollte Ottakringer und
			 * Starkenberger liefern
			 */

			// test findBars
			/*
			 * Collection<Integer> array = new ArrayList<>();
			 * array.add(beerid3); Collection<IBar> testbars3 =
			 * handler.findBars(47, 48, 11, 12, array); //sollte 11er Haus
			 * liefern
			 */

			// delete tests
			/*
			 * handler.deletePost(post1); Collection<IBeer> testbeers7 =
			 * handler.getAllBeersFromBar(barid1); //sollte nur Stiegl liefern
			 * Collection<IBeerPost> testposts6 =
			 * handler.getAllPostsFromUser(userid1); //sollte Post 3 liefern
			 * Collection<IBeerPost> testposts7 =
			 * handler.getAllPostsFromBar(barid1); //sollte Post2 liefern
			 */
			handler.deleteUser(userid1);
			/*
			 * Collection<IBeerPost> testposts8 = handler.getAllPosts();
			 * //sollte Post2,4,5 liefern handler.deleteBeer(beerid3);
			 * Collection<IBeerPost> testposts9 = handler.getAllPosts();
			 * //sollte Post2,4 liefern
			 */

=======
			
			//get user
			IUser user = handler.getUserLogin("user2", "user2");
			
			//assign Achievements
			Collection<IAchievement> ach = handler.getAllAchievements();
			handler.assignAchievementToUser(user.getId(), achid1);
			handler.assignAchievementToUser(user.getId(), achid2);
			
		/*	//all get methods
			Collection<IBeer> beers1 = handler.getAllBeers();
			Collection<IBeer> bars1 = handler.getAllBeersFromBar(barid1);
			Collection<IBeer> bars2 = handler.getAllBeersFromBar(barid2);

			Collection<IBeerPost> posts1 = handler.getAllPostsFromBar(barid1);
			Collection<IBeerPost> posts2 = handler.getAllPostsFromBar(barid2);
			Collection<IBeerPost> userposts1 = handler.getAllPostsFromUser(userid1);
			Collection<IBeerPost> userposts2 = handler.getAllPostsFromUser(userid2);

			Collection<IBar> bars3 = handler.getBarsCoordinates(11.34, 11.35,
					47.26, 47.27);
			
			Collection<IAchievement> userAch = handler.getAllAchievementsFromUser(user.getId());*/
			//Collection<IBeerPost> posts = handler.getAllPosts();

			// fehler erzeugen
			/*try {
				handler.getAchievementByID(99999);
			} catch (Exception e) {
				System.out.println("Fehler achievement");
			}
			try {
				handler.deleteBar(99999);
			} catch (Exception e) {
				System.out.println("Fehler delete");
			}
			
*/
			
>>>>>>> origin/db
			handler.closeDatabaseConnection();
		} catch (Exception e) {
			return;
		}
	}
}

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

			// create new users
			int userid1 = handler.createUser("admin", "admin@gmx.at", "admin", true).getId();
			int userid2 = handler.createUser("user2", "user2@gmx.at", "user2", false).getId();
			int userid3 = handler.createUser("user3", "user3@gmx.at", "user3", false).getId();

			// create achievement critera
			int crit1 = handler
					.createAchievementCriterion(AchievementCriterionType.TOTAL_POSTS, 5).getId();
			int crit2 = handler
					.createAchievementCriterion(AchievementCriterionType.TOTAL_POSTS, 10).getId();
			int crit3 = handler
					.createAchievementCriterion(AchievementCriterionType.TOTAL_POSTS, 20).getId();
			int crit4 = handler.createAchievementCriterion(AchievementCriterionType.WEEK_POSTS, 3)
					.getId();
			int crit5 = handler.createAchievementCriterion(AchievementCriterionType.WEEK_POSTS, 5)
					.getId();
			int crit6 = handler.createAchievementCriterion(AchievementCriterionType.WEEK_POSTS, 10)
					.getId();

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
					"Traditionelles Lokal in Innsbruck, das ein eigenes Bier braut",
					"www.brauwirtshaus.at").getId();

			int barid2 = handler
					.createBar(
							"11er Haus",
							47.268653,
							11.392825,
							"Sehr bekannte Bar in Innsbruck, die sehr viele Biersorten der ganzen Welt anbietet.",
							"http://innsbruckplus.at/elferhaus/").getId();
			int barid3 = handler
					.createBar(
							"Stadtcafe",
							47.268785,
							11.395963,
							"Bekanntestes Nachtlokal in Innsbruck, das hauptsächlich von der Studentenszene besucht wird.",
							"www.tagnacht.at/stadtcafe/").getId();

			// create new achievement
			int achid1 = handler
					.createAchievement("5 posts",
							"The user will get this achievement when he drinks 5 beers and add a post to the app")
					.getId();
			int achid2 = handler
					.createAchievement("10 posts",
							"The user will get this achievement when he drinks 10 beers and add a post to the app")
					.getId();
			int achid3 = handler
					.createAchievement("20 posts",
							"The user will get this achievement when he drinks 20 beers and add a post to the app")
					.getId();
			int achid4 = handler
					.createAchievement("3 posts in one week",
							"The user will get this achievement when he make 3 posts in one week")
					.getId();
			int achid5 = handler
					.createAchievement("5 posts in one week",
							"The user will get this achievement when he make 5 posts in one week")
					.getId();
			int achid6 = handler
					.createAchievement("10 posts in one week",
							"The user will get this achievement when he make 10 posts in one week")
					.getId();
			int achid7 = handler
					.createAchievement("BRONZE BEER",
							"The user will get the bronze beer if he make 5 posts in total and make 3 posts in one week")
					.getId();
			int achid8 = handler
					.createAchievement("SILVER BEER",
							"The user will get the bronze beer if he make 10 posts in total and make 5 posts in one week")
					.getId();
			int achid9 = handler
					.createAchievement("GOLD BEER",
							"The user will get the bronze beer if he make 20 posts in total and make 10 posts in one week")
					.getId();

			// assign criterion to achievements
			handler.assignCriterionToAchievement(achid1, crit1);
			handler.assignCriterionToAchievement(achid2, crit2);
			handler.assignCriterionToAchievement(achid3, crit3);
			handler.assignCriterionToAchievement(achid4, crit4);
			handler.assignCriterionToAchievement(achid5, crit5);
			handler.assignCriterionToAchievement(achid6, crit6);
			handler.assignCriterionToAchievement(achid7, crit1);
			handler.assignCriterionToAchievement(achid8, crit2);
			handler.assignCriterionToAchievement(achid9, crit3);
			handler.assignCriterionToAchievement(achid7, crit4);
			handler.assignCriterionToAchievement(achid8, crit5);
			handler.assignCriterionToAchievement(achid9, crit6);

			// create posts
			int post1 = handler.createPost(barid1, beerid1, userid1, 3.0, 2, "Post1").getId();
			int post2 = handler.createPost(barid1, beerid2, userid2, 3.0, 2, "Post2").getId();
			int post3 = handler.createPost(barid2, beerid3, userid1, 3.0, 2, "Post3").getId();
			int post4 = handler.createPost(barid2, beerid4, userid3, 3.0, 2, "Post4").getId();
			int post5 = handler.createPost(barid2, beerid3, userid2, 3.0, 2, "Post5").getId();

			// test getbyID methods
			Collection<IAchievementCriterion> testachcrit1 = handler.getAllCriterionFromAchievement(achid1);
			Collection<IAchievementCriterion> testachcrit2 = handler.getAllCriterionFromAchievement(achid2);
			Collection<IAchievementCriterion> testachcrit3 = handler.getAllCriterionFromAchievement(achid3);
			Collection<IAchievementCriterion> testachcrit4 = handler.getAllCriterionFromAchievement(achid4);
			Collection<IAchievementCriterion> testachcrit5 = handler.getAllCriterionFromAchievement(achid5);
			Collection<IAchievementCriterion> testachcrit6 = handler.getAllCriterionFromAchievement(achid6);
			Collection<IAchievementCriterion> testachcrit7 = handler.getAllCriterionFromAchievement(achid7);
			Collection<IAchievementCriterion> testachcrit8 = handler.getAllCriterionFromAchievement(achid8);
			Collection<IAchievementCriterion> testachcrit9 = handler.getAllCriterionFromAchievement(achid9);

			// test user login
			/*
			 * IUser testuser1 = handler.getUserLogin("admin", "admin"); IUser
			 * testuser2 = handler.getUserLogin("user2", "user2"); IUser
			 * testuser3 = handler.getUserLoginAdmin("admin", "admin"); IUser
			 * testuser4 = handler.getUserLoginAdmin("user3", "user3");
			 * 
			 * // test get all beers and verify method Collection<IBeer>
			 * testbeers1 = handler.getAllBeers(); Collection<IBeer> testbeers2
			 * = handler.getAllBeersVerified(); Collection<IBeer> testbeers3 =
			 * handler.getAllBeersUnverified(); handler.verifyBeer(beerid1);
			 * handler.verifyBeer(beerid3); Collection<IBeer> testbeers4 =
			 * handler.getAllBeers(); Collection<IBeer> testbeers5 =
			 * handler.getAllBeersVerified(); Collection<IBeer> testbeers6 =
			 * handler.getAllBeersUnverified();
			 * 
			 * // test getAll methods from model class Collection<IUser>
			 * testusers1 = handler.getAllUsers(); Collection<IBar> testbars1 =
			 * handler.getAllBars(); Collection<IAchievement> testachievements1
			 * = handler.getAllAchievements(); Collection<IBeerPost> testposts1
			 * = handler.getAllPosts();
			 * 
			 * // test getBarsfromCoordinates Collection<IBar> testbars2 =
			 * handler.getBarsCoordinates(47.2687, 48, 11.39, 12); // sollte nur
			 * TheresienBräu und Stadtcafe liefern
			 * 
			 * // test all other get methods Collection<IBeerPost> testposts2 =
			 * handler.getAllPostsFromBar(barid1); // sollte Post1,2 liefern
			 * Collection<IBeerPost> testposts3 =
			 * handler.getAllPostsFromBar(barid2); // sollte Post 3,4,5 liefern
			 * Collection<IBeerPost> testposts4 =
			 * handler.getAllPostsFromUser(userid1); // sollte Post1,3 liefern
			 * Collection<IBeerPost> testposts5 =
			 * handler.getAllPostsFromUser(userid2); // sollte Post 2,5 liefer
			 * Collection<IBeer> testbeers5 =
			 * handler.getAllBeersFromBar(barid1); // sollte Gösser und Stiegl
			 * liefern Collection<IBeer> testbeers6 =
			 * handler.getAllBeersFromBar(barid2); // sollte Ottakringer und
			 * Starkenberger liefern
			 * 
			 * // test findBars Collection<Integer> array = new ArrayList<>();
			 * array.add(beerid3); Collection<IBar> testbars3 =
			 * handler.findBars(47, 48, 11, 12, array); // sollte 11er Haus
			 * liefern
			 * 
			 * // delete tests handler.deletePost(post1); Collection<IBeer>
			 * testbeers7 = handler.getAllBeersFromBar(barid1); // sollte nur
			 * Stiegl liefern Collection<IBeerPost> testposts6 =
			 * handler.getAllPostsFromUser(userid1); // sollte Post 3 liefern
			 * Collection<IBeerPost> testposts7 =
			 * handler.getAllPostsFromBar(barid1); // sollte Post2 liefern
			 * 
			 * handler.deleteUser(userid1);
			 * 
			 * Collection<IBeerPost> testposts8 = handler.getAllPosts(); //
			 * sollte Post2,4,5 liefern handler.deleteBeer(beerid3);
			 * Collection<IBeerPost> testposts9 = handler.getAllPosts(); //
			 * sollte Post2,4 liefern
			 */

			handler.closeDatabaseConnection();
		} catch (Exception e) {
			return;
		}
	}
}

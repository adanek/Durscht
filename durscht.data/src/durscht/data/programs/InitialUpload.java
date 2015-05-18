package durscht.data.programs;

import durscht.contracts.data.IDataHandler;
import durscht.data.handler.DataHandler;

public class InitialUpload {

	public static void main(String[] args) {
		DataHandler.setTestDB(true);
		// get handler
		IDataHandler handler = new DataHandler();

		// create new users
		int userid1 = handler.createUser("admin", "admin@gmx.at", "admin", true).getId();
		int userid2 = handler.createUser("user2", "user2@gmx.at", "user2", false).getId();
		int userid3 = handler.createUser("user3", "user3@gmx.at", "user3", false).getId();

		// create new beers
		int beerid1 = handler.createBeer("Gösser", "Radler", "Herbes österreichisches Bier", false)
				.getId();
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
				.createAchievement("500 posts",
						"The user will get this achievement when he drinks 500 beers and add a post to the app")
				.getId();
		int achid2 = handler
				.createAchievement("50 posts",
						"The user will get this achievement when he drinks 50 beers and add a post to the app")
				.getId();
		int achid3 = handler
				.createAchievement("100 posts",
						"The user will get this achievement when he drinks 100 beers and add a post to the app")
				.getId();

		// create posts
		int post1 = handler.createPost(barid1, beerid1, userid1, 3.0, 2, "Post1").getId();
		int post2 = handler.createPost(barid1, beerid2, userid2, 3.0, 2, "Post2").getId();
		int post3 = handler.createPost(barid2, beerid3, userid1, 3.0, 2, "Post3").getId();
		int post4 = handler.createPost(barid2, beerid4, userid3, 3.0, 2, "Post4").getId();
		int post5 = handler.createPost(barid2, beerid3, userid2, 3.0, 2, "Post5").getId();

		handler.closeDatabaseConnection();

	}

}

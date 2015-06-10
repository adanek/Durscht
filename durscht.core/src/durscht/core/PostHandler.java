package durscht.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IAchievementCriterion;
import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.logic.model.IPost;
import durscht.core.config.ServiceLocator;
import durscht.model.Achievement;
import durscht.model.Bar;
import durscht.model.Beer;
import durscht.model.Post;

public class PostHandler implements IPostHandler {

	private final double BAR_SEARCH_RADIUS = 2.5;
	private final String BLACKLIST_FILE_LOCATION = "/resources/blacklist.txt";
	private Collection<String> blacklist = new ArrayList<String>();

	private IDataHandler dataHandler;

	private IDataHandler getDataHandler() {
		if (dataHandler == null)
			dataHandler = ServiceLocator.getDataHandler();

		return dataHandler;
	}

	public void setDataHandler(IDataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public PostHandler() {
		/* Set up Blacklist Collection, containing all forbidden words */
		InputStream src = PostHandler.class.getResourceAsStream(BLACKLIST_FILE_LOCATION);
		try (Scanner in = new Scanner(src);) {
			while (in.hasNext()) {
				this.blacklist.add(in.next());
			}
		}
	}

	@Override
	public durscht.contracts.logic.model.IBar[] getNearBars(double latitude, double longitude)
			throws IllegalArgumentException, IllegalStateException {
		if (Math.abs(latitude) > 90 || Math.abs(longitude) > 180) {
			throw new IllegalArgumentException("invalid latitude or longitude");
		}

		IDataHandler dataHandler = getDataHandler();

		double latitudeOffset = Position.calcLongitudeOffset(latitude, BAR_SEARCH_RADIUS);
		double longitudeOffset = Position.calcLatitudeOffset(BAR_SEARCH_RADIUS);

		Collection<IBar> nearIBarsList = dataHandler.getBarsCoordinates(latitude - latitudeOffset, latitude
				+ latitudeOffset, longitude - longitudeOffset, longitude + longitudeOffset);

		Collection<Bar> nearBarsList = new ArrayList<Bar>();

		for (IBar ibar : nearIBarsList) {
			nearBarsList.add(BarHandler.convertDBtoUI(ibar, latitude, longitude));
			/*
			 * Bar bar = new Bar(); bar.setName(ibar.getName()); bar.setId(ibar.getId());
			 * bar.setDistance(calcDistanceBetweenPoints(latitude, longitude, ibar.getLatitude(), ibar.getLongitude()));
			 * nearBarsList.add(bar);
			 */
		}

		return nearBarsList.toArray(new Bar[nearBarsList.size()]);

	}

	@Override
	public durscht.contracts.logic.model.IBeer[] getBeersByBar(int barID) throws IllegalArgumentException,
			IllegalStateException {
		IDataHandler dataHandler = getDataHandler();

		Collection<IBeer> IBeersList = dataHandler.getAllBeersFromBar(barID);
		Collection<Beer> beersList = new ArrayList<Beer>();
		for (IBeer ibeer : IBeersList) {
			Beer beer = new Beer();
			beer.setId(ibeer.getId());
			beer.setBrand(ibeer.getBrand());
			beer.setType(ibeer.getType());
			beer.setDescription(ibeer.getDescription());
			beersList.add(beer);
		}

		return beersList.toArray(new Beer[beersList.size()]);
	}

	@Override
	public Integer putPosting(int barID, int beerID, int userID, double prize, int rating, String description)
			throws IllegalArgumentException, IllegalStateException {

		IDataHandler dataHandler = getDataHandler();

		description = replaceBlackWords(description);

		IBeerPost post = dataHandler.createPost(barID, beerID, userID, prize, rating, description);

		// achievementAlgorithm(userID);

		return post.getId();
	}

	/**
	 * Replaces every occurrence of a blacklist-word within a user input by asterisks.
	 *
	 * @param comment User input string.
	 * @return modified user input string after masking out all blacklist-words.
	 */
	private String replaceBlackWords(String comment) {
		/**
		 * regex patterns
		 *
		 * \\bWORD\\b : matches an exact word 'WORD' \\bWORD\\B : matches any word with prefix 'WORD' WORD : matches
		 * every occurrence of 'WORD'
		 */

		for (String word : blacklist) {
			Pattern stopWords = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
			Matcher matcher = stopWords.matcher(comment);
			comment = matcher.replaceAll(new String(new char[word.length()]).replace("\0", "*"));
		}

		return comment;
	}

	@Override
	public durscht.contracts.logic.model.IAchievement[] getAchievementsByUser(int userID)
			throws IllegalArgumentException, IllegalStateException {
		IDataHandler dataHandler = getDataHandler();
		Collection<IAchievement> allAchievements = dataHandler.getAllAchievements();
		Collection<IAchievement> userAchievements = dataHandler.getAllAchievementsFromUser(userID);
		Collection<IBeerPost> userPosts = dataHandler.getAllPostsFromUser(userID);

		Collection<durscht.contracts.logic.model.IAchievement> returnList = new ArrayList<>();

		for (IAchievement achievement : allAchievements) {
			Achievement newAchievement = new Achievement();
			newAchievement.setName(achievement.getName());
			// imageUrl is the lowercase achievement-name (where spaces are removed!)
			newAchievement.setImageUrl("/resources/" + achievement.getName().replace(" ", "").toLowerCase() + ".png");

			if (userAchievements.contains(achievement)) {
				// return 100%
				newAchievement.setProgress(100);
			}
			else {
				// user does not have achievement 100% -> check now!
				Collection<IAchievementCriterion> criteria = dataHandler.getAllCriterionFromAchievement(achievement
						.getId());
				int progress = 0;
				for (IAchievementCriterion criterion : criteria) {
					switch (criterion.getType()) {
					case TOTAL_POSTS:
						progress += checkTotalPostsAchievement(userID, userPosts.size(), criterion.getValue());
						break;
					case WEEK_POSTS:
						progress += checkPostsByTimeAchievement(userID, userPosts, criterion.getValue(), 7);
						break;
					case MONTH_POSTS:
						progress += checkPostsByTimeAchievement(userID, userPosts, criterion.getValue(), 30);
						break;
					}
				}
				int endProgress = progress / criteria.size();
				if (endProgress == 100) {
					// user satisfies all criterion for the achievement -> save in DB
					dataHandler.assignAchievementToUser(userID, achievement.getId());
				}
				newAchievement.setProgress(endProgress);
			}
			returnList.add(newAchievement);
		}

		return returnList.toArray(new Achievement[returnList.size()]);
	}

	private int checkTotalPostsAchievement(int userID, int userPostCount, int value) throws IllegalArgumentException,
			IllegalStateException {
		if (userPostCount >= value) {
			// criterion satisfied!
			return 100;
		}

		// criterion not satisfied! Calculate criterion progress
		return (100 * userPostCount) / value;
	}

	private int checkPostsByTimeAchievement(int userID, Collection<IBeerPost> userPosts, int value, int days)
			throws IllegalArgumentException, IllegalStateException {
		// <days> days ago from exactly now
		final long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date startDate = new Date(System.currentTimeMillis() - (days * DAY_IN_MS));

		int postCount = 0;
		for (IBeerPost post : userPosts) {
			if (post.getTimeDate().after(startDate)) {
				postCount++;
				if (postCount >= value) {
					// criterion satisfied!
					return 100;
				}
			}
		}

		// criterion not satisfied! Calculate criterion progress
		return (100 * postCount) / value;
	}

	@Override
	public IPost[] getAllPosts() throws IllegalStateException {

		IDataHandler dataHandler = getDataHandler();

		Collection<IBeerPost> iposts = dataHandler.getAllPosts();

		Collection<Post> postList = new ArrayList<Post>();

		for (IBeerPost ipost : iposts) {
			postList.add(convertDBtoUI(ipost));
		}

		return postList.toArray(new Post[postList.size()]);
	}

	@Override
	public void deletePost(int postID) throws IllegalArgumentException {
		getDataHandler().deletePost(postID);
	}

	/**
	 * Converts a DB IBeerPost to a UI IPost object.
	 *
	 * @param ipost IBeerPost Post object from the database
	 * @return UI IPost object containing ipost's information
	 */
	protected static Post convertDBtoUI(IBeerPost ipost) {
		Post post = new Post();
		post.setId(ipost.getId());
		post.setUsername(ipost.getUser().getName());
		post.setDate(ipost.getTimeDate());
		post.setBeer(ipost.getBeer().getBrand() + " - " + ipost.getBeer().getType());
		post.setRating(ipost.getRating());
		post.setPrice(ipost.getPrice());
		post.setDescription(ipost.getDescription());

		return post;
	}

}

package durscht.core;

import java.util.ArrayList;
import java.util.Collection;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.contracts.ui.IPost;
import durscht.core.config.ServiceLocator;

public class PostHandler implements IPostHandler {

	private final double BAR_SEARCH_RADIUS = 2.5;

	private IDataHandler dataHandler;

	private IDataHandler getDataHandler() {
		if (dataHandler == null)
			dataHandler = ServiceLocator.getDataHandler();

		return dataHandler;
	}

	public void setDataHandler(IDataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	@Override
	public durscht.contracts.ui.IBar[] getNearBars(double latitude, double longitude) throws IllegalArgumentException,
			IllegalStateException {
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
			nearBarsList.add(BarHandler.convertDBtoUI(ibar));
			/*
			 * Bar bar = new Bar(); bar.setName(ibar.getName()); bar.setId(ibar.getId());
			 * bar.setDistance(calcDistanceBetweenPoints(latitude, longitude, ibar.getLatitude(), ibar.getLongitude()));
			 * nearBarsList.add(bar);
			 */
		}

		return nearBarsList.toArray(new Bar[nearBarsList.size()]);

	}

	@Override
	public durscht.contracts.ui.IBeer[] getBeersByBar(int barID) throws IllegalArgumentException, IllegalStateException {
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

		IBeerPost post = dataHandler.createPost(barID, beerID, userID, prize, rating, description);

		achievementAlgorithm(userID);

		return post.getId();
	}

	private void achievementAlgorithm(int userID) {

	}

	@Override
	public durscht.contracts.ui.IBar createNewBar(String name, double latitude, double longitude, String description,
			String url) throws IllegalStateException {

		IDataHandler dataHandler = getDataHandler();

		IBar ibar = dataHandler.createBar(name, latitude, longitude, description, url);

		Bar bar = new Bar();
		bar.setDistance(Position.calcDistanceBetweenPoints(latitude, longitude, ibar.getLatitude(), ibar.getLongitude()));
		bar.setId(ibar.getId());
		bar.setName(ibar.getName());

		return bar;
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

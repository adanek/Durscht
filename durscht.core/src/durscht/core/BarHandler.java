package durscht.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IBarHandler;
import durscht.contracts.logic.model.IPost;
import durscht.core.config.ServiceLocator;
import durscht.model.Bar;
import durscht.model.Post;

public class BarHandler implements IBarHandler {

	private final double BAR_SEARCH_RADIUS = 30;

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
	public durscht.contracts.logic.model.IBar[] findBars(double latitude, double longitude, List<Integer> favoriteBeers)
			throws IllegalArgumentException, IllegalStateException {
		if (Math.abs(latitude) > 90 || Math.abs(longitude) > 180) {
			throw new IllegalArgumentException("invalid latitude or longitude");
		}
		if (favoriteBeers.size() < 1) {
			throw new IllegalArgumentException("favoriteBeers has to have at least one element");
		}

		IDataHandler dataHandler = getDataHandler();

		double latitudeOffset = Position.calcLongitudeOffset(latitude, BAR_SEARCH_RADIUS);
		double longitudeOffset = Position.calcLatitudeOffset(BAR_SEARCH_RADIUS);

		Collection<IBar> ibars = dataHandler.findBars(latitude - latitudeOffset, latitude + latitudeOffset, longitude
				- longitudeOffset, longitude + longitudeOffset, favoriteBeers);

		Collection<Bar> nearBarsList = new ArrayList<Bar>();

		for (IBar ibar : ibars) {
			nearBarsList.add(convertDBtoUI(ibar, latitude, longitude));
		}

		return nearBarsList.toArray(new Bar[nearBarsList.size()]);
	}

	@Override
	public IPost[] getPostsFromBar(int barID) throws IllegalArgumentException, IllegalStateException {
		IDataHandler dataHandler = getDataHandler();

		Collection<IBeerPost> iposts = dataHandler.getAllPostsFromBar(barID);

		Collection<Post> postList = new ArrayList<Post>();

		for (IBeerPost ipost : iposts) {
			postList.add(PostHandler.convertDBtoUI(ipost));
		}

		return postList.toArray(new Post[postList.size()]);
	}

	@Override
	public durscht.contracts.logic.model.IBar[] getAllBars() throws IllegalStateException {
		IDataHandler dataHandler = getDataHandler();

		Collection<IBar> ibars = dataHandler.getAllBars();

		Collection<Bar> barList = new ArrayList<Bar>();

		for (IBar ibar : ibars) {
			barList.add(convertDBtoUI(ibar, ibar.getLatitude(), ibar.getLongitude()));
		}

		return barList.toArray(new Bar[barList.size()]);
	}

	@Override
	public void deleteBar(int barID) throws IllegalArgumentException {
		getDataHandler().deleteBar(barID);
	}

	@Override
	public durscht.contracts.logic.model.IBar createNewBar(String name, double latitude, double longitude,
			String description, String url) throws IllegalStateException {
		IDataHandler dataHandler = getDataHandler();

		IBar ibar = dataHandler.createBar(name, latitude, longitude, description, url);

		return BarHandler.convertDBtoUI(ibar, latitude, longitude);
	}

	protected static Bar convertDBtoUI(IBar ibar, double currentLat, double currentLon) {
		Bar bar = new Bar();
		bar.setId(ibar.getId());
		bar.setName(ibar.getName());
		bar.setUrl(ibar.getUrl());
		bar.setLatitude(ibar.getLatitude());
		bar.setLongitude(ibar.getLongitude());
		bar.setDistance(Position.calcDistanceBetweenPoints(currentLat, currentLon, ibar.getLatitude(),
				ibar.getLongitude()));

		return bar;
	}
}

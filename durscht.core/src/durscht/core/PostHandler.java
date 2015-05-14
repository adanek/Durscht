package durscht.core;

import java.util.ArrayList;
import java.util.Collection;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.core.config.ServiceLocator;

public class PostHandler implements IPostHandler {

	// private final double BAR_SEARCH_RADIUS = 15.0;

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

		return getNearBars(latitude, longitude, 3.0);

	}

	private durscht.contracts.ui.IBar[] getNearBars(double latitude, double longitude, double search_radius)
			throws IllegalArgumentException, IllegalStateException {

		IDataHandler dataHandler = getDataHandler();

		double latitudeOffset = calcLongitudeOffset(latitude, search_radius);
		double longitudeOffset = calcLatitudeOffset(search_radius);

		Collection<IBar> nearIBarsList = dataHandler.getBarsCoordinates(latitude - latitudeOffset, latitude
				+ latitudeOffset, longitude - longitudeOffset, longitude + longitudeOffset);

		Collection<Bar> nearBarsList = new ArrayList<Bar>();

		for (IBar ibar : nearIBarsList) {
			Bar bar = new Bar();
			bar.setName(ibar.getName());
			bar.setId(ibar.getId());
			bar.setDistance(calcDistanceBetweenPoints(latitude, longitude, ibar.getLatitude(), ibar.getLongitude()));
			nearBarsList.add(bar);
		}

		return nearBarsList.toArray(new Bar[nearBarsList.size()]);
	}

	/**
	 * Calculates the longitude offset of a point which is distance kilometers away
	 * 
	 * @param latitude Latitude of the initial point
	 * @param distance displacement of the point whose longitude offset is calculated
	 * @return longitude degree offset of a point distance kilometers away
	 * @throws IllegalArgumentException invalid latitude data: latitude outside [-90,90]
	 */
	private double calcLongitudeOffset(double latitude, double distance) throws IllegalArgumentException {
		if (Math.abs(latitude) > 90) {
			throw new IllegalArgumentException("invalid latitude");
		}

		final double earthRadius = 6371; // [km]

		return Math.toDegrees(distance / (earthRadius * Math.cos(Math.toRadians(latitude))));
	}

	/**
	 * Calculates the latitude offset of a point which is distance kilometers away
	 * 
	 * @param distance displacement of the point whose latitude offset is calculated
	 * @return latitude degree offset of a point distance kilometers away
	 */
	private double calcLatitudeOffset(double distance) {
		final double earthRadius = 6371; // [km]

		return Math.toDegrees(distance / earthRadius);
	}

	/**
	 * Calculates the distance between two points given in longitude/latitude format.
	 * 
	 * @see http ://stackoverflow.com/questions/18861728/calculating-distance-between
	 *      -two-points-represented-by-lat-long-upto-15-feet-acc
	 * 
	 * @param lat1 Latitude of point #1
	 * @param lon1 Longitude of point #1
	 * @param lat2 Latitude of point #2
	 * @param lon2 Longitude of point #2
	 * @return distance in kilometers [double]
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude
	 *         outside [-90,90]
	 */
	private double calcDistanceBetweenPoints(double lat1, double lon1, double lat2, double lon2)
			throws IllegalArgumentException {
		if (Math.abs(lat1) > 90 || Math.abs(lat2) > 90 || Math.abs(lon1) > 180 || Math.abs(lon2) > 180) {
			throw new IllegalArgumentException("invalid latitude or longitude");
		}

		final double earthRadius = 6371; // average radius of the earth in km
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRadius * c;
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
		bar.setDistance(calcDistanceBetweenPoints(latitude, longitude, ibar.getLatitude(), ibar.getLongitude()));
		bar.setId(ibar.getId());
		bar.setName(ibar.getName());

		return bar;
	}

	@Override
	public durscht.contracts.ui.IBar[] getBarsByBeer(double latitude, double longitude,
			durscht.contracts.ui.IBeer[] beers) {

		IDataHandler dataHandler = getDataHandler();
		Collection<durscht.contracts.ui.IBar> filteredBars = new ArrayList<>();

		// get all near bars
		durscht.contracts.ui.IBar[] ibars = getNearBars(latitude, longitude, 5);

		// filter bars for beers that are in the beers array
		for (durscht.contracts.ui.IBar ibar : ibars) {
			Collection<IBeer> ibeers = dataHandler.getAllBeersFromBar(ibar.getId());

			// look through all registered beers of every bar
			for (IBeer ibeer : ibeers) {

				for (durscht.contracts.ui.IBeer beer : beers) {
					if (ibeer.getId() == beer.getId()) {
						filteredBars.add(ibar);
						break;
					}
				}
			}
		}

		return filteredBars.toArray(new durscht.contracts.ui.IBar[filteredBars.size()]);
	}
}

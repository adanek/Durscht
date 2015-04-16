package durscht.core;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeer;
import durscht.contracts.data.IDataHandler;
import durscht.contracts.logic.IPostHandler;
import durscht.core.config.ServiceLocator;

public class PostHandler implements IPostHandler {
	
	private IDataHandler dataHandler;
	
	private IDataHandler getDataHandler() {
		if (dataHandler == null)
			dataHandler = ServiceLocator.getDataHandler();
		
		return dataHandler;
	}
	
	public void setDataHandler(IDataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	/**
	 * Provides an array of bars surrounding a given center-point
	 * 
	 * @param latitude Latitude of center-point
	 * @param longitude Longitude of center-point
	 * @return Array of Bars around the passed point
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude outside [-90,90]
	 */
	@Override
	public Bar[] getNearBars(double latitude, double longitude) throws IllegalArgumentException, NoSuchElementException {
		if (Math.abs(latitude) > 90 || Math.abs(longitude) > 180 )
			throw new IllegalArgumentException("invalid latitude or longitude");
		
		IDataHandler dataHandler = getDataHandler();
		
		double latitudeOffset = calcLongitudeOffset(latitude, 5);
		double longitudeOffset = calcLatitudeOffset(5);
		
		Collection<IBar> nearIBarsList = dataHandler.getBarsCoordinates(latitude - latitudeOffset, latitude + latitudeOffset, longitude - longitudeOffset, longitude + longitudeOffset);
		if (nearIBarsList == null)
			throw new NoSuchElementException("no bar found");
		
		Collection<Bar> nearBarsList = new ArrayList<Bar>();
		
		for (IBar ibar : nearIBarsList) {
			Bar bar = new Bar();
			Collection<IBeer> IBeersList = dataHandler.getAllBeersFromBar(ibar.getId());
			Collection<Beer> beersList = new ArrayList<Beer>();
			for (IBeer ibeer : IBeersList) {
				Beer beer = new Beer();
				beer.setBrand(ibeer.getName());
				beer.setType(ibeer.getName());
				beer.setDescription(ibeer.getDescription());
				beersList.add(beer);
			}
			bar.setBeers(beersList.toArray(new Beer[beersList.size()]));
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
		if (Math.abs(latitude) > 90)
			throw new IllegalArgumentException("invalid latitude");
		
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
	 * @see http://stackoverflow.com/questions/18861728/calculating-distance-between-two-points-represented-by-lat-long-upto-15-feet-acc
	 * 
	 * @param lat1 Latitude of point #1
	 * @param lon1 Longitude of point #1
	 * @param lat2 Latitude of point #2
	 * @param lon2 Longitude of point #2
	 * @return distance in kilometers [double]
	 * @throws IllegalArgumentException invalid longitude or latitude data: longitude outside [-180,180] or latitude outside [-90,90]
	 */
	private double calcDistanceBetweenPoints(double lat1, double lon1, double lat2, double lon2) throws IllegalArgumentException {
		if (Math.abs(lat1) > 90 || Math.abs(lat2) > 90 || Math.abs(lon1) > 180 || Math.abs(lon2) > 180 )
			throw new IllegalArgumentException("invalid latitude or longitude");
		
		final double earthRadius = 6371; // average radius of the earth in km
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	       Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) 
	      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    return earthRadius * c;
	}

	@Override
	public int putPosting(int barID, int beerID, int userID, String description) {
		IDataHandler dataHandler = getDataHandler();
		
		int returnID;
		if ((returnID = dataHandler.createPost(barID, beerID, userID, description)) == 0){
			// Exception
		}
		
		awardAlgorithm(userID);
		
		return returnID; 
	}
	
	private void awardAlgorithm(int userID) {
		
	}

	@Override
	public int createNewBar(String name, double latitude, double longitude, String description, String url) {
		IDataHandler dataHandler = getDataHandler();
		
		return dataHandler.createBar(name, latitude, longitude, description, url);
	}

	@Override
	public int createNewBeer(String name, String description) {
		IDataHandler dataHandler = getDataHandler();
		
		return dataHandler.createBeer(name, description);
	}

}

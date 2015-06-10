package durscht.core;

/**
 * Utility class for position- and geo-location-calculations
 *
 */
public class Position {

	/**
	 * Calculates the longitude offset of a point which is distance kilometers away
	 * 
	 * @param latitude Latitude of the initial point
	 * @param distance displacement of the point whose longitude offset is calculated
	 * @return longitude degree offset of a point distance kilometers away
	 * @throws IllegalArgumentException invalid latitude data: latitude outside [-90,90]
	 */
	protected static double calcLongitudeOffset(double latitude, double distance) throws IllegalArgumentException {
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
	protected static double calcLatitudeOffset(double distance) {
		final double earthRadius = 6371; // [km]

		return Math.toDegrees(distance / earthRadius);
	}

	/**
	 * Calculates the distance between two points given in longitude/latitude format.
	 * 
	 * @see http://stackoverflow.com/questions/18861728/calculating-distance-between
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
	protected static double calcDistanceBetweenPoints(double lat1, double lon1, double lat2, double lon2)
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

}

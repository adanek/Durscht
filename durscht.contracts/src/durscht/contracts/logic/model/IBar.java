package durscht.contracts.logic.model;

/**
 * Represents a bar where users can have a beer
 */
public interface IBar {

	/**
	 * Gets the id of the bar
	 * 
	 * @return the id of the bar
	 */
	int getId();

	/**
	 * Gets the Name of the bar
	 * 
	 * @return the name of the bar
	 */
	String getName();

	/**
	 * Gets the Website URL of the bar
	 * 
	 * @return website URL of the bar
	 */
	String getUrl();

	/**
	 * Gets the distance from the current position of the user to the bar
	 * 
	 * @return the distance to the bar
	 */
	double getDistance();

	/**
	 * Gets the latitude on which the bar is located
	 * 
	 * @return the latitude of the bar
	 */
	double getLatitude();

	/**
	 * Gets the longitude on which the bar is located
	 * 
	 * @return the longitude of the bar
	 */
	double getLongitude();
}

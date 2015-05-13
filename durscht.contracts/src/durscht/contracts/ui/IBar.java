package durscht.contracts.ui;

/**
 * Represents a bar where users can have a beer
 */
public interface IBar {

	/**
	 * Gets the id of the bar
	 * @return the id of the bar
	 */
	int getId();

	/**
	 * Gets the Name of the bar
	 * @return the name of the bar
	 */
	String getName();

	/**
	 * Gets the distance from the current position of the user to the bar
	 * @return the distance to the bar
	 */
	double getDistance();
}

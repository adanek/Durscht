package durscht.contracts.logic.model;

/**
 * Represents a beer of a specific brand and type
 */
public interface IBeer {

	/**
	 * Gets the id of the beer
	 * @return the id of the beer
	 */
    int getId();

	/**
	 * Gets the brand of the beer
	 * @return the brand of the beer
	 */
	String getBrand();

	/**
	 * Gets the type of the beer
	 * @return the type of the beer
	 */
	String getType();

	/**
	 * Gets a short description of the beer
	 * @return a short description of the beer
	 */
	String getDescription();	
}

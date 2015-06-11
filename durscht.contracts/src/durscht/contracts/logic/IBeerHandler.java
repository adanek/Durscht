package durscht.contracts.logic;

import durscht.contracts.logic.model.IBeer;

public interface IBeerHandler {

	/**
	 * Returns all verified beers with the given prefix. This method is case insensitive.
	 *
	 * @param prefix
	 * @return Array of IBeer
	 */
	IBeer[] getBeersByPrefix(String prefix);

	/**
	 * Creates new beer, which is not verified
	 *
	 * @param brand
	 * @param type
	 * @param description
	 * @return new IBeer
	 * @throws IllegalStateException
	 */
	IBeer createNewBeer(String brand, String type, String description) throws IllegalStateException;

	/**
	 * Returns all verified beers
	 *
	 * @return Array of IBeer
	 */
	IBeer[] getAllBeersVerified();

	/**
	 * Returns all not verified beers
	 *
	 * @return Array of IBeer
	 */
	IBeer[] getAllBeersNotVerified();

	/**
	 * Verifies a beer and adds it to cached beers
	 *
	 * @param id
	 * @return verified beer
	 */
	IBeer verifyBeer(int id);

	/**
	 * Deletes an unverified beer
	 *
	 * @param id
	 * @throws IllegalArgumentException if beer is verified
	 */
	void deleteBeer(int id) throws IllegalArgumentException;
}

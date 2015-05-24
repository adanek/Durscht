package durscht.contracts.logic;

import durscht.contracts.logic.model.IBeer;

public interface IBeerHandler {

	/**
	 * Returns all verified beers with prefix <prefix>. This method is case insensitive.
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
	 * Verifies a beer
	 *
	 * @param uiBeer
	 * @return verified beer
	 */
	public IBeer verifyBeer(IBeer uiBeer);
}

package durscht.contracts.logic.model;

/**
 * Represents an user of the app
 */
public interface IUser {

	/**
	 * Gets the identifier of the user
	 * @return the id of the user
	 */
	int getId();

	/**
	 * Gets the name of the user
	 * @return the name of the user
	 */
	String getName();

	/**
	 * Gets the email address of th user
	 * @return the email address of the user
	 */
	String getEmail();

	/**
	 * Gets the joined date of the user
	 * @return the joined date of the user
	 */
	String getJoinedDate();
}

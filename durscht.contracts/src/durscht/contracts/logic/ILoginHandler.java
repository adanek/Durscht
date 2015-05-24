package durscht.contracts.logic;

import durscht.contracts.logic.model.IUser;

/**
 * Created by Mark on 04.05.2015.
 */
public interface ILoginHandler {
	/**
	 * Login
	 *
	 * @param name
	 * @param password
	 * @return linked User or null, if he does not exist
	 */
	IUser login(String name, String password);

	/**
	 * Login
	 *
	 * @param name
	 * @param password
	 * @return linked User or null, if he does not exist or does not have admin rights
	 */
	IUser adminLogin(String name, String password);

	/**
	 * Creates new User
	 *
	 * @param name
	 * @param password
	 * @param email
	 * @return new User
	 */
	IUser createUser(String name, String password, String email);
}

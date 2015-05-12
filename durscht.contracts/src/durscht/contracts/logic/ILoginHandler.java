package durscht.contracts.logic;

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
    public durscht.contracts.logic.IUser login(String name,String password);

    /**
     * Creates new User
     *
     * @param name
     * @param password
     * @param email
     * @return new User
     */
    public durscht.contracts.logic.IUser createUser(String name, String password, String email);
}

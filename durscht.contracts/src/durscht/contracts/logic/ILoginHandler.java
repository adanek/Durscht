package durscht.contracts.logic;

/**
 * Created by Mark on 04.05.2015.
 */
public interface ILoginHandler {
    public durscht.contracts.logic.IUser login(String name,String password);

    public durscht.contracts.logic.IUser createUser(String name, String password, String email);
}

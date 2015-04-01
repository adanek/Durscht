package durscht.contracts;

public interface IDataHandler {

	//create user
	public Integer createUser(String name, String email, String password);
	
	//create beer
	public Integer createBeer(String name, String description);
	
	public IUser getUserLogin(String name, String password);
	
	public void closeDatabaseConnection();
	
}

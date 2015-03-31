package durscht.contracts;

public interface IDataHandler {

	//create user
	public void createUser(String name, String email, String password);
	
	//create beer
	public void createBeer(String name, String description);
	
}

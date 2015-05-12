package durscht.contracts.ui;

public interface IUser {

    void setName(String name);	
 	void setEmail(String email);	
 	void setPassword(String password);
	String getName();
	String getEmail();
	String getPassword();
	String getJoinedDate();
	int getId();
}

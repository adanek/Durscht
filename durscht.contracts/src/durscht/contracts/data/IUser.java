package durscht.contracts.data;

import java.util.Date;

public interface IUser {

	int getId();

	String getName();

	String getEmail();

	Date getJoinedDate();
	
	boolean isAdmin();

}
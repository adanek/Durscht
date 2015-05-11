package durscht.contracts.data;

import java.util.Collection;
import java.util.Date;

public interface IUser {

	public int getId();

	public String getName();

	public String getEmail();

	public Date getJoinedDate();
	
	public boolean isAdmin();

}
package durscht.contracts.data;

import java.util.Collection;
import java.util.Date;

import durscht.contracts.data.*;

public interface IUser {

	public int getId();

	public String getName();

	public String getEmail();

	public Date getJoinedDate();
	
	public boolean isAdmin();

}
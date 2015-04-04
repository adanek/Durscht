package durscht.contracts;

import java.util.Date;

public interface IUser {

	public int getId();

	public String getName();

	public String getEmail();

	public Date getJoinedDate();

}
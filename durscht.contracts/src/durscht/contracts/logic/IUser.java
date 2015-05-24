package durscht.contracts.logic;

import java.util.Date;

/**
 * Created by Mark on 04.05.2015.
 */
public interface IUser {

	int getId();

	String getName();

	String getEmail();

	Date getJoinedDate();
}

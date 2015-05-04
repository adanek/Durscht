package durscht.contracts.logic;

import java.util.Date;

/**
 * Created by Mark on 04.05.2015.
 */
public interface IUser {

    public int getId();

    public String getName();

    public String getEmail();

    public Date getJoinedDate();
}

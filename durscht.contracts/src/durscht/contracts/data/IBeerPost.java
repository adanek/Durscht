package durscht.contracts.data;

import java.util.Date;

public interface IBeerPost {

	String getDescription();

	int getId();

	double getPrice();

	int getRating();

	IUser getUser();

	IBeer getBeer();

	IBar getBar();

	Date getTimeDate();
}

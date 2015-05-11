package durscht.contracts.data;

import java.util.Date;

public interface IBeerPost {
	public String getDescription();
	
	public int getId();
	
	public double getPrice();
	
	public int getRating();	
	
	public IUser getUser();

	public IBeer getBeer();

	public IBar getBar();

	public Date getTimeDate();
}

package durscht.contracts.logic;

import durscht.contracts.ui.IBar;


public interface IPostHandler {
	
	public IBar[] getNearBars(double longitude, double latitude);
	
	public Integer putPosting(int barID, int beerID, int userID, String description);
	
	public Integer createNewBar(String name, double latitude, double longitude, String description, String url);
}

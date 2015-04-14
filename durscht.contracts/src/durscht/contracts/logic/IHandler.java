package durscht.contracts.logic;


public interface IHandler {

	// return value has to be changed to IBar from UI-contracts
	public void getNearBars(double longitude, double latitude);
	
	public void putPosting();
}

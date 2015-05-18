package durscht.contracts.data;

public interface IBar {

	int getId();

	String getName();

	String getUrl();

	double getLatitude();

	double getLongitude();

	String getDescription();
	
	boolean isVerified();
}
package durscht.contracts.data;

public interface IBeer {

	int getId();
	
	String getBrand();

	String getType();
	
	boolean isVerified();

	String getDescription();
}

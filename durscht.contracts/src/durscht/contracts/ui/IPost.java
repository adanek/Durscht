package durscht.contracts.ui;

public interface IPost {

 	void setRating(int rating);
	void setPrice(double price);
	void setDescription(String description);
	int getRating();
	double getPrice();
	String getDescription();
	int getId();
}

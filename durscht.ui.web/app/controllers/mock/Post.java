package controllers.mock;



import durscht.contracts.logic.model.IPost;

import java.util.Date;


public class Post implements IPost {

    private String username;
    private int rating;
    private String beer;
    private double price;
    private String description;
    private int id;
    private Date date;

    public Post(String username, int rating, String beer, double price, String description, int id, Date date) {
        this.username = username;
        this.rating = rating;
        this.beer = beer;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public Date getDate(){ return date;  }

    public String getUsername() { return username; }

    public int getRating() {
        return rating;
    }

    public String getBeer() {
        return beer;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}

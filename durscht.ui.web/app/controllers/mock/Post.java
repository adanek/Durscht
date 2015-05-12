package controllers.mock;


public class Post {

    public String username;
    public String bar;
    public String beer;
    public double price;
    public String description;
    public int id;

    public Post(String username, String bar, String beer, double price, String description, int id) {
        this.username = username;
        this.bar = bar;
        this.beer = beer;
        this.price = price;
        this.description = description;
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public void setBeer(String beer) {
        this.beer = beer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {

        return username;
    }

    public String getBar() {
        return bar;
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

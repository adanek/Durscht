package durscht.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IUser;

@Entity
@Table(name = "BeerPost")
public class BeerPost implements IBeerPost{
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private SavedUser user;
	@ManyToOne
	private Beer beer;
	@ManyToOne
	private Bar bar;
	private double price;
	private int rating;
	@Lob
	private String description;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public SavedUser getUser() {
		return user;
	}
	
	public void setUser(SavedUser user) {
		this.user = user;
	}
	
	public Beer getBeer() {
		return beer;
	}
	
	public void setBeer(Beer beer) {
		this.beer = beer;
	}
	
	public Bar getBar() {
		return bar;
	}
	
	public void setBar(Bar bar) {
		this.bar = bar;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}

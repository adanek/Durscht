package durscht.model;

import java.util.Date;

import durscht.contracts.ui.IPost;

public class Post implements IPost {

	private Integer id;
	private String username;
	private Date date;
	private String beer;
	private Integer rating;
	private double price;
	private String description;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getBeer() {
		return beer;
	}

	@Override
	public int getRating() {
		return rating;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setBeer(String beer) {
		this.beer = beer;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

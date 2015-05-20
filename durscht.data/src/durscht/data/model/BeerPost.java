package durscht.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import durscht.contracts.data.IBar;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IUser;

@Entity
@Table(name = "BeerPost")
public class BeerPost implements IBeerPost {
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
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeDate = new Date();

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

	public Date getTimeDate() {
		return timeDate;
	}

	public void setTimeDate(Date timeDate) {
		this.timeDate = timeDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeerPost other = (BeerPost) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

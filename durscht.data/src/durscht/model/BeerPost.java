package durscht.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import durscht.contracts.IBar;
import durscht.contracts.IBeerPost;
import durscht.contracts.IUser;

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
	@Lob
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IUser getUser() {
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

	public IBar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

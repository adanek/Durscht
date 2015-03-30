package durscht.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BeerPost")
public class BeerPost {
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

	public int getid() {
		return id;
	}

	public void setid(int id) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

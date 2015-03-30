package durscht.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Bar")
public class Bar {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(name = "web_url")
	private String url;
	@Column(name = "pos_lat")
	private double latitude;
	@Column(name = "pos_long")
	private double longitude;
	@Lob
	private String description;
	@OneToMany(mappedBy = "bar")
	private Collection<BeerPost> beerPosts = new LinkedList<>();

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<BeerPost> getBeerPosts() {
		return beerPosts;
	}

	public void setBeerPosts(Collection<BeerPost> beerPosts) {
		this.beerPosts = beerPosts;
	}

}

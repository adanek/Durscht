package durscht.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Beer")
public class Beer {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Lob
	private String description;
	@OneToMany(mappedBy = "beer")
	private Collection<BeerPost> beerPosts = new LinkedList<>();

	public int getid() {
		return id;
	}

	public void setUid(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

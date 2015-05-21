package durscht.data.model;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IBeerPost;
import durscht.contracts.data.IUser;

@Entity
@Table(name = "SavedUser")
public class SavedUser implements IUser {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String password;
	private boolean admin;
	@Temporal(TemporalType.DATE)
	private Date joinedDate = new Date();
	@OneToMany(mappedBy = "user")
	@Cascade({ CascadeType.DELETE })
	private Collection<BeerPost> beerPosts = new LinkedList<>();
	//@ManyToMany
	//private Collection<Achievement> achievements = new LinkedList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Collection<BeerPost> getBeerPosts() {
		return beerPosts;
	}

	public void setBeerPosts(Collection<BeerPost> beerPosts) {
		this.beerPosts = beerPosts;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SavedUser other = (SavedUser) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

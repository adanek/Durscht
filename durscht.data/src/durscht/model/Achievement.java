package durscht.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import durscht.contracts.IAchievement;

@Entity
@Table(name = "Achievement")
public class Achievement implements IAchievement {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Lob
	private String description;
	@ManyToMany(mappedBy = "achievements")
	private Collection<SavedUser> users = new LinkedList<>();

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<SavedUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<SavedUser> users) {
		this.users = users;
	}

}

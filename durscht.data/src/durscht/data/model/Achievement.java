package durscht.data.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

import durscht.contracts.data.IAchievement;
import durscht.contracts.data.IAchievementCriterion;

@Entity
@Table(name = "Achievement")
public class Achievement implements IAchievement {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Lob
	private String description;
	// @ManyToMany(mappedBy = "achievements")
	// private Collection<SavedUser> users = new LinkedList<>();
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private Collection<AchievementCriterion> criterion = new LinkedList<>();

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

	public Collection<IAchievementCriterion> getCriterion() {
		return new LinkedList<IAchievementCriterion>(criterion);
	}

	public void setCriterion(Collection<AchievementCriterion> criterion) {
		this.criterion = criterion;
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
		Achievement other = (Achievement) obj;
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

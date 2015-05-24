package durscht.data.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import durscht.contracts.data.AchievementCriterionType;
import durscht.contracts.data.IAchievementCriterion;

@Entity
@Table(name = "AchievementCriterion")
public class AchievementCriterion implements IAchievementCriterion {
	@Id
	@GeneratedValue
	private int id;
	private AchievementCriterionType type;
	private int value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AchievementCriterionType getType() {
		return type;
	}

	public void setType(AchievementCriterionType type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}

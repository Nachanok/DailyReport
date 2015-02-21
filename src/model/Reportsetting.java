package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the reportsetting database table.
 * 
 */
@Entity
@NamedQuery(name="Reportsetting.findAll", query="SELECT r FROM Reportsetting r")
public class Reportsetting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String groupCategory;

	private String description;

	private int groupLevel;

	public Reportsetting() {
	}

	public String getGroupCategory() {
		return this.groupCategory;
	}

	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getGroupLevel() {
		return this.groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

}
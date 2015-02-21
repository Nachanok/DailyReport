package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the holiday database table.
 * 
 */
@Entity
@NamedQuery(name = "Holiday.findAll", query = "SELECT h FROM Holiday h")
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String date;

	private String descriptions;

	private int enabled;

	public Holiday() {
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

}
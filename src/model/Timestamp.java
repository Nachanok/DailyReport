package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the timestamp database table.
 * 
 */
@Entity
@NamedQuery(name="Timestamp.findAll", query="SELECT t FROM Timestamp t")
public class Timestamp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TimestampPK id;

	private String department;

	private String name;

	private Time timeIn;

	private Time timeout;

	public Timestamp() {
	}

	public TimestampPK getId() {
		return this.id;
	}

	public void setId(TimestampPK id) {
		this.id = id;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getTimeIn() {
		return this.timeIn;
	}

	public void setTimeIn(Time timeIn) {
		this.timeIn = timeIn;
	}

	public Time getTimeout() {
		return this.timeout;
	}

	public void setTimeout(Time timeout) {
		this.timeout = timeout;
	}

}
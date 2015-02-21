package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usertasklist database table.
 * 
 */
@Entity
@NamedQuery(name="Usertasklist.findAll", query="SELECT u FROM Usertasklist u")
public class Usertasklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	public Usertasklist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the privatetasklist database table.
 * 
 */
@Embeddable
public class PrivatetasklistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String username;

	private String taskCode;

	public PrivatetasklistPK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTaskCode() {
		return this.taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrivatetasklistPK)) {
			return false;
		}
		PrivatetasklistPK castOther = (PrivatetasklistPK)other;
		return 
			this.username.equals(castOther.username)
			&& this.taskCode.equals(castOther.taskCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.taskCode.hashCode();
		
		return hash;
	}
}
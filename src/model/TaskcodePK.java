package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the taskcode database table.
 * 
 */
@Embeddable
public class TaskcodePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String taskGroupCode;

	private String subtaskCode;

	public TaskcodePK() {
	}
	public String getTaskGroupCode() {
		return this.taskGroupCode;
	}
	public void setTaskGroupCode(String taskGroupCode) {
		this.taskGroupCode = taskGroupCode;
	}
	public String getSubtaskCode() {
		return this.subtaskCode;
	}
	public void setSubtaskCode(String subtaskCode) {
		this.subtaskCode = subtaskCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TaskcodePK)) {
			return false;
		}
		TaskcodePK castOther = (TaskcodePK)other;
		return 
			this.taskGroupCode.equals(castOther.taskGroupCode)
			&& this.subtaskCode.equals(castOther.subtaskCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.taskGroupCode.hashCode();
		hash = hash * prime + this.subtaskCode.hashCode();
		
		return hash;
	}
}
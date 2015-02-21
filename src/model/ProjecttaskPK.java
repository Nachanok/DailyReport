package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the projecttask database table.
 * 
 */
@Embeddable
public class ProjecttaskPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String projectCode;

	@Column(insertable=false, updatable=false)
	private String versionCode;

	@Column(insertable=false, updatable=false)
	private String taskGroupCode;

	@Column(insertable=false, updatable=false)
	private String subtaskCode;

	public ProjecttaskPK() {
	}
	public String getProjectCode() {
		return this.projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getVersionCode() {
		return this.versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
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
		if (!(other instanceof ProjecttaskPK)) {
			return false;
		}
		ProjecttaskPK castOther = (ProjecttaskPK)other;
		return 
			this.projectCode.equals(castOther.projectCode)
			&& this.versionCode.equals(castOther.versionCode)
			&& this.taskGroupCode.equals(castOther.taskGroupCode)
			&& this.subtaskCode.equals(castOther.subtaskCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectCode.hashCode();
		hash = hash * prime + this.versionCode.hashCode();
		hash = hash * prime + this.taskGroupCode.hashCode();
		hash = hash * prime + this.subtaskCode.hashCode();
		
		return hash;
	}
}
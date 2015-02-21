package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the projectversion database table.
 * 
 */
@Embeddable
public class ProjectversionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String projectCode;

	private String versionCode;

	public ProjectversionPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProjectversionPK)) {
			return false;
		}
		ProjectversionPK castOther = (ProjectversionPK)other;
		return 
			this.projectCode.equals(castOther.projectCode)
			&& this.versionCode.equals(castOther.versionCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.projectCode.hashCode();
		hash = hash * prime + this.versionCode.hashCode();
		
		return hash;
	}
}
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the usertimesheet database table.
 * 
 */
@Embeddable
public class UsertimesheetPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable = false, updatable = false)
	private String username;

	private String date;

	private String customerCode;

	@Column(insertable = false, updatable = false)
	private String projectCode;

	@Column(insertable = false, updatable = false)
	private String versionCode;

	@Column(insertable = false, updatable = false)
	private String taskGroupCode;

	@Column(insertable = false, updatable = false)
	private String subtaskCode;

	public UsertimesheetPK() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
		if (!(other instanceof UsertimesheetPK)) {
			return false;
		}
		UsertimesheetPK castOther = (UsertimesheetPK) other;
		return this.username.equals(castOther.username) && this.date.equals(castOther.date)
				&& this.customerCode.equals(castOther.customerCode) && this.projectCode.equals(castOther.projectCode)
				&& this.versionCode.equals(castOther.versionCode) && this.taskGroupCode.equals(castOther.taskGroupCode)
				&& this.subtaskCode.equals(castOther.subtaskCode);
	}

	public boolean withInWeek(String date) throws ParseException {
		String givenFormat = date; // date.substring(6) + "/" +
									// date.substring(4, 6) + "/" +
									// date.substring(0, 4);
		// System.out.println(date);
		// System.out.println(givenFormat);
		String haveFormat = this.date.substring(6) + "/" + this.date.substring(4, 6) + "/" + this.date.substring(0, 4);
		// System.out.println(haveFormat);
		Date given = new SimpleDateFormat("MM/dd/yyyy").parse(givenFormat);
		Date have = new SimpleDateFormat("dd/MM/yyyy").parse(haveFormat);
		Calendar c = Calendar.getInstance();
		c.setTime(given);
		int givenYear = c.get(Calendar.YEAR);
		int givenWeekOfYear = c.get(Calendar.WEEK_OF_YEAR);
		c.setTime(have);
		int haveYear = c.get(Calendar.YEAR);
		int haveWeekOfYear = c.get(Calendar.WEEK_OF_YEAR);

		return (givenYear == haveYear) && (givenWeekOfYear == haveWeekOfYear);
	}

	public int dayOfWeek() throws ParseException {
		String format = this.date.substring(6) + "/" + this.date.substring(4, 6) + "/" + this.date.substring(0, 4);
		Date day = new SimpleDateFormat("dd/MM/yyyy").parse(format);
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.date.hashCode();
		hash = hash * prime + this.customerCode.hashCode();
		hash = hash * prime + this.projectCode.hashCode();
		hash = hash * prime + this.versionCode.hashCode();
		hash = hash * prime + this.taskGroupCode.hashCode();
		hash = hash * prime + this.subtaskCode.hashCode();

		return hash;
	}

	public String toString() {
		return projectCode + versionCode + taskGroupCode + subtaskCode;
	}

	public String regularFormat() {
		return projectCode + "." + versionCode + "." + taskGroupCode + "." + subtaskCode;
	}
}
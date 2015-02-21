package model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the usertimesheet database table.
 * 
 */
@Entity
@NamedQuery(name = "Usertimesheet.findAll", query = "SELECT u FROM Usertimesheet u")
public class Usertimesheet implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsertimesheetPK id;

	private int approved;

	private int deliverableCount;

	@Lob
	private String deliverableDes;

	@Lob
	private String descriptions;

	private float usageTime;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "Username")
	private Account account;

	// bi-directional many-to-one association to Projecttask
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ProjectCode", referencedColumnName = "ProjectCode"),
			@JoinColumn(name = "SubtaskCode", referencedColumnName = "SubtaskCode"),
			@JoinColumn(name = "TaskGroupCode", referencedColumnName = "TaskGroupCode"),
			@JoinColumn(name = "VersionCode", referencedColumnName = "VersionCode") })
	private Projecttask projecttask;

	public Usertimesheet() {
	}

	public UsertimesheetPK getId() {
		return this.id;
	}

	public void setId(UsertimesheetPK id) {
		this.id = id;
	}

	public int getApproved() {
		return this.approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getDeliverableCount() {
		return this.deliverableCount;
	}

	public void setDeliverableCount(int deliverableCount) {
		this.deliverableCount = deliverableCount;
	}

	public String getDeliverableDes() {
		return this.deliverableDes;
	}

	public void setDeliverableDes(String deliverableDes) {
		this.deliverableDes = deliverableDes;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public float getUsageTime() {
		return this.usageTime;
	}

	public void setUsageTime(float usageTime) {
		this.usageTime = usageTime;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Projecttask getProjecttask() {
		return this.projecttask;
	}

	public void setProjecttask(Projecttask projecttask) {
		this.projecttask = projecttask;
	}

}
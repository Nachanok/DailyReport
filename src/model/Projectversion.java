package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the projectversion database table.
 * 
 */
@Entity
@NamedQuery(name = "Projectversion.findAll", query = "SELECT p FROM Projectversion p")
public class Projectversion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectversionPK id;

	private int billable;

	private int enable;

	// bi-directional many-to-one association to Projecttask
	@OneToMany(mappedBy = "projectversion")
	private List<Projecttask> projecttasks;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "Leader")
	private Account account;

	// bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name = "ProjectCode")
	private Project project;

	public Projectversion() {
	}

	public ProjectversionPK getId() {
		return this.id;
	}

	public void setId(ProjectversionPK id) {
		this.id = id;
	}

	public int getBillable() {
		return this.billable;
	}

	public void setBillable(int billable) {
		this.billable = billable;
	}

	public int getEnable() {
		return this.enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public List<Projecttask> getProjecttasks() {
		return this.projecttasks;
	}

	public void setProjecttasks(List<Projecttask> projecttasks) {
		this.projecttasks = projecttasks;
	}

	public Projecttask addProjecttask(Projecttask projecttask) {
		getProjecttasks().add(projecttask);
		projecttask.setProjectversion(this);

		return projecttask;
	}

	public Projecttask removeProjecttask(Projecttask projecttask) {
		getProjecttasks().remove(projecttask);
		projecttask.setProjectversion(null);

		return projecttask;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
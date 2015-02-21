package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the projecttask database table.
 * 
 */
@Entity
@NamedQuery(name = "Projecttask.findAll", query = "SELECT p FROM Projecttask p")
public class Projecttask implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjecttaskPK id;

	private int enable;

	private String projectTaskDes;

	private int taskType;

	// bi-directional many-to-one association to Projectversion
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "ProjectCode", referencedColumnName = "ProjectCode"),
			@JoinColumn(name = "VersionCode", referencedColumnName = "VersionCode") })
	private Projectversion projectversion;

	// bi-directional many-to-one association to Taskcode
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "SubtaskCode", referencedColumnName = "SubtaskCode"),
			@JoinColumn(name = "TaskGroupCode", referencedColumnName = "TaskGroupCode") })
	private Taskcode taskcode;

	// bi-directional many-to-one association to Usertimesheet
	@OneToMany(mappedBy = "projecttask")
	private List<Usertimesheet> usertimesheets;

	public Projecttask() {
	}

	public ProjecttaskPK getId() {
		return this.id;
	}

	public void setId(ProjecttaskPK id) {
		this.id = id;
	}

	public int getEnable() {
		return this.enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getProjectTaskDes() {
		return this.projectTaskDes;
	}

	public void setProjectTaskDes(String projectTaskDes) {
		this.projectTaskDes = projectTaskDes;
	}

	public int getTaskType() {
		return this.taskType;
	}

	public void setTaskType(int taskType) {
		this.taskType = taskType;
	}

	public Projectversion getProjectversion() {
		return this.projectversion;
	}

	public void setProjectversion(Projectversion projectversion) {
		this.projectversion = projectversion;
	}

	public Taskcode getTaskcode() {
		return this.taskcode;
	}

	public void setTaskcode(Taskcode taskcode) {
		this.taskcode = taskcode;
	}

	public List<Usertimesheet> getUsertimesheets() {
		return this.usertimesheets;
	}

	public void setUsertimesheets(List<Usertimesheet> usertimesheets) {
		this.usertimesheets = usertimesheets;
	}

	public Usertimesheet addUsertimesheet(Usertimesheet usertimesheet) {
		getUsertimesheets().add(usertimesheet);
		usertimesheet.setProjecttask(this);

		return usertimesheet;
	}

	public Usertimesheet removeUsertimesheet(Usertimesheet usertimesheet) {
		getUsertimesheets().remove(usertimesheet);
		usertimesheet.setProjecttask(null);

		return usertimesheet;
	}

}
package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the taskcode database table.
 * 
 */
@Entity
@NamedQuery(name="Taskcode.findAll", query="SELECT t FROM Taskcode t")
public class Taskcode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TaskcodePK id;

	private String subtaskName;

	private String taskCodeDes;

	private String taskGroupName;

	//bi-directional many-to-one association to Projecttask
	@OneToMany(mappedBy="taskcode")
	private List<Projecttask> projecttasks;

	public Taskcode() {
	}

	public TaskcodePK getId() {
		return this.id;
	}

	public void setId(TaskcodePK id) {
		this.id = id;
	}

	public String getSubtaskName() {
		return this.subtaskName;
	}

	public void setSubtaskName(String subtaskName) {
		this.subtaskName = subtaskName;
	}

	public String getTaskCodeDes() {
		return this.taskCodeDes;
	}

	public void setTaskCodeDes(String taskCodeDes) {
		this.taskCodeDes = taskCodeDes;
	}

	public String getTaskGroupName() {
		return this.taskGroupName;
	}

	public void setTaskGroupName(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}

	public List<Projecttask> getProjecttasks() {
		return this.projecttasks;
	}

	public void setProjecttasks(List<Projecttask> projecttasks) {
		this.projecttasks = projecttasks;
	}

	public Projecttask addProjecttask(Projecttask projecttask) {
		getProjecttasks().add(projecttask);
		projecttask.setTaskcode(this);

		return projecttask;
	}

	public Projecttask removeProjecttask(Projecttask projecttask) {
		getProjecttasks().remove(projecttask);
		projecttask.setTaskcode(null);

		return projecttask;
	}

}
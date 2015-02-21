package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
public class Project implements Serializable {
	@Override
	public String toString() {
		return "Project [projectName=" + projectName + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	private String projectCode;

	private String projectDes;

	private String projectName;

	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name = "CustomerCode")
	private Customer customer;

	// bi-directional many-to-one association to Projectversion
	@OneToMany(mappedBy = "project")
	private List<Projectversion> projectversions;

	public Project() {
	}

	public String getProjectCode() {
		return this.projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectDes() {
		return this.projectDes;
	}

	public void setProjectDes(String projectDes) {
		this.projectDes = projectDes;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Projectversion> getProjectversions() {
		return this.projectversions;
	}

	public void setProjectversions(List<Projectversion> projectversions) {
		this.projectversions = projectversions;
	}

	public Projectversion addProjectversion(Projectversion projectversion) {
		getProjectversions().add(projectversion);
		projectversion.setProject(this);

		return projectversion;
	}

	public Projectversion removeProjectversion(Projectversion projectversion) {
		getProjectversions().remove(projectversion);
		projectversion.setProject(null);

		return projectversion;
	}

}
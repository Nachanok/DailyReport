package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String customerCode;

	private String customerDes;

	private String customerName;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CategoryCode")
	private Category category;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="customer")
	private List<Project> projects;

	public Customer() {
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerDes() {
		return this.customerDes;
	}

	public void setCustomerDes(String customerDes) {
		this.customerDes = customerDes;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setCustomer(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setCustomer(null);

		return project;
	}

}
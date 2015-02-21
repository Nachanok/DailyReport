package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departmentitem database table.
 * 
 */
@Entity
@NamedQuery(name="Departmentitem.findAll", query="SELECT d FROM Departmentitem d")
public class Departmentitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private String descriptions;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="departmentitem")
	private List<Account> accounts;

	public Departmentitem() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setDepartmentitem(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setDepartmentitem(null);

		return account;
	}

}
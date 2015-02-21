package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the privilegeitem database table.
 * 
 */
@Entity
@NamedQuery(name="Privilegeitem.findAll", query="SELECT p FROM Privilegeitem p")
public class Privilegeitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private int id;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="privilegeitem")
	private List<Account> accounts;

	public Privilegeitem() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setPrivilegeitem(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setPrivilegeitem(null);

		return account;
	}

}
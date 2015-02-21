package model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 45)
	private String username;

	@Column(nullable = false, length = 45)
	private String department;

	@Column(nullable = false, length = 45)
	private String email;

	@Column(nullable = false, length = 45)
	private String enabled;

	@Column(length = 100)
	private String fullname;

	@Column(nullable = false, length = 45)
	private String password;

	@Column(nullable = false, length = 45)
	private String privilege;

	@Column(length = 45)
	private String role;

	private int userID;

	// bi-directional many-to-one association to Departmentitem

	@PrimaryKeyJoinColumn(name = "department")
	private Departmentitem departmentitem;

	// bi-directional many-to-one association to Privilegeitem

	@PrimaryKeyJoinColumn(name = "privilege")
	private Privilegeitem privilegeitem;

	// bi-directional many-to-one association to Privatetasklist
	@OneToMany(mappedBy = "account")
	private List<Privatetasklist> privatetasklists;

	// bi-directional many-to-one association to Projectversion
	@OneToMany(mappedBy = "account")
	private List<Projectversion> projectversions;

	// bi-directional many-to-one association to Usertimesheet
	@OneToMany(mappedBy = "account")
	private List<Usertimesheet> usertimesheets;
	
	private String time;

	public Account() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTime(){
		return time;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	public Departmentitem getDepartmentitem() {
		return this.departmentitem;
	}

	public void setDepartmentitem(Departmentitem departmentitem) {
		this.departmentitem = departmentitem;
	}

	public Privilegeitem getPrivilegeitem() {
		return this.privilegeitem;
	}

	public void setPrivilegeitem(Privilegeitem privilegeitem) {
		this.privilegeitem = privilegeitem;
	}

	public List<Privatetasklist> getPrivatetasklists() {
		return this.privatetasklists;
	}

	public void setPrivatetasklists(List<Privatetasklist> privatetasklists) {
		this.privatetasklists = privatetasklists;
	}

	public Privatetasklist addPrivatetasklist(Privatetasklist privatetasklist) {
		getPrivatetasklists().add(privatetasklist);
		privatetasklist.setAccount(this);

		return privatetasklist;
	}

	public Privatetasklist removePrivatetasklist(Privatetasklist privatetasklist) {
		getPrivatetasklists().remove(privatetasklist);
		privatetasklist.setAccount(null);

		return privatetasklist;
	}

	public List<Projectversion> getProjectversions() {
		return this.projectversions;
	}

	public void setProjectversions(List<Projectversion> projectversions) {
		this.projectversions = projectversions;
	}

	public Projectversion addProjectversion(Projectversion projectversion) {
		getProjectversions().add(projectversion);
		projectversion.setAccount(this);

		return projectversion;
	}

	public Projectversion removeProjectversion(Projectversion projectversion) {
		getProjectversions().remove(projectversion);
		projectversion.setAccount(null);

		return projectversion;
	}

	public List<Usertimesheet> getUsertimesheets() {
		return this.usertimesheets;
	}

	public void setUsertimesheets(List<Usertimesheet> usertimesheets) {
		this.usertimesheets = usertimesheets;
	}

	public Usertimesheet addUsertimesheet(Usertimesheet usertimesheet) {
		getUsertimesheets().add(usertimesheet);
		usertimesheet.setAccount(this);

		return usertimesheet;
	}

	public Usertimesheet removeUsertimesheet(Usertimesheet usertimesheet) {
		getUsertimesheets().remove(usertimesheet);
		usertimesheet.setAccount(null);

		return usertimesheet;
	}

	public List<List<Usertimesheet>> timesheetInWeek(String date) throws ParseException {
		List<List<Usertimesheet>> lists = new ArrayList<List<Usertimesheet>>();
		List<Usertimesheet> weeklists = new ArrayList<Usertimesheet>();
		for (Usertimesheet item : usertimesheets) {
			if (item.getId().withInWeek(date)) {
				weeklists.add(item);
			}
		}
		// System.out.println(weeklists.size() + " week");
		List<String> task = new ArrayList<String>();
		for (int i = 0; i < weeklists.size(); i++) {
			List<Usertimesheet> findSameTask = new ArrayList<Usertimesheet>();
			if (!(task.contains(weeklists.get(i).getId().toString()))) {
				findSameTask.add(weeklists.get(i));
				for (int j = i + 1; j < weeklists.size(); j++) {
					if (weeklists.get(i).getId().toString().equals(weeklists.get(j).getId().toString())) {
						findSameTask.add(weeklists.get(j));
					}
				}
				task.add(weeklists.get(i).getId().toString());
			}
			// System.out.println(findSameTask.size() + " findsametask");
			if (!findSameTask.isEmpty()) {
				List<Usertimesheet> altered = alterList(findSameTask);
				lists.add(altered);
			}
		}
		// for (int i = 0; i < lists.size(); i++) {
		// for (int j = 0; j < lists.get(i).size(); j++) {
		// System.out.println(lists.get(i).get(j).getId().toString());
		// }
		// }
		return lists;
	}

	public List<Usertimesheet> alterList(List<Usertimesheet> lists) throws ParseException {
		List<Usertimesheet> altered = new ArrayList<Usertimesheet>();
		for (int i = 0; i < 7; i++) {
			Usertimesheet fakesheet = new Usertimesheet();
			fakesheet.setUsageTime(0);
			fakesheet.setId(lists.get(0).getId());
			for (int j = 0; j < lists.size(); j++) {
				if (lists.get(j).getId().dayOfWeek() == (i + 1)) {
					altered.add(lists.get(j));
					break;
				}
			}
			if (altered.size() == i)
				altered.add(fakesheet);
		}
		return altered;
	}

}
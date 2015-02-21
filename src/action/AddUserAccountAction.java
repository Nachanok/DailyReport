package action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


//import java.sql.*;
import model.Account;
import model.Privilegeitem;
import model.Departmentitem;
import dao.ConcreteAccDAO;
import dao.ConcreteDaoFactory;
import dao.ConcretePrivilegeitemDAO;
import dao.ConcreteDepartmentitemDAO;


public class AddUserAccountAction {
	
	private ConcreteAccDAO accDAO;
	private Account accSelect;
	private List<Account> alluser;
	private String department;
	private List<Departmentitem> departmentitem;

	private String email;
	private String enabled;
	private String password;
	private String privilege;
	private List<Privilegeitem> privilegeitem;
	private String role;
	//private String selectremoveuser;
	private String time;
	private int userID;
	private List<Account> userLink = new ArrayList<Account>();
	private List<Account> userList;
	private String username;
	private ConcretePrivilegeitemDAO privilegeitemDAO;
	private ConcreteDepartmentitemDAO departmentitemDAO;
	
	public AddUserAccountAction(){
		privilegeitemDAO = (ConcretePrivilegeitemDAO) ConcreteDaoFactory.getInstance().createDAO(
				"privilegeitemDAO");
		privilegeitem = privilegeitemDAO.findAll();
		
		departmentitemDAO =  (ConcreteDepartmentitemDAO) ConcreteDaoFactory.getInstance().createDAO(
				"departmentitemDAO");
		departmentitem = departmentitemDAO.findAll();
		
		accDAO = (ConcreteAccDAO) ConcreteDaoFactory.getInstance().createDAO("userDAO");
		//userMap = new HashMap();
		Account selected = new Account();
		//System.out.println(accDAO.findAll().size());
		userList = accDAO.findAll();
		//System.out.println(userList.size());

		for (int i = 0; i < userList.size(); i++) {
			selected = userList.get(i);
			userLink.add(selected);
//			String userName = selected.getUsername();
//			String passWord = selected.getPassword();
			/*String departMent = selected.getDepartment();
			String eMail = selected.getEmail();
			String tIme = selected.getTime();
			String priVilege = selected.getPrivilege();
			String rOle = selected.getRole();
			int uSerID = selected.getUserID();*/
			List<String> userNames = new ArrayList<String>();
			userNames.add(selected.getUsername() + "."
						+ selected.getPassword() + "."
						+ selected.getDepartment() + "."
						+ selected.getEmail() + "."
						+ selected.getTime() + "."
						+ selected.getPrivilege() + "."
						+ selected.getRole() + "."
						+ selected.getUserID());
			//String userIP = userNames + ".";
			//userLink.put(userIP, userNames);
			//userLink2.addAll(userNames);
		}
	}
	public String userlink(){
		alluser = accDAO.findAll();
		return "success";
	}


	public String disable(){
//		try{
//			 Class.forName("oracle.jdbc.driver.OracleDriver");  
//			  Connection con=DriverManager.getConnection(  
//			    "jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
//			  PreparedStatement ps=con.prepareStatement("select * from ");  
//			  ResultSet rs=ps.executeQuery();  
//		}
//		catch(Exception e){e.printStackTrace();} 
		accSelect = accDAO.findByName(username);

//		accSelect.setUsername(username);
//		accSelect.setPassword(password);
//		accSelect.setDepartment(department);
//		accSelect.setEmail(email);
//		accSelect.setPrivilege(privilege);
//		accSelect.setRole(role);
//		accSelect.setTime(time);
//		accSelect.setUserID(userID);
//		String userfullname = null;
//		accSelect.setFullname(userfullname);
		accDAO.delete(accSelect);
		
		accSelect.setEnabled("NO");
		
		accDAO.save(accSelect);
		alluser = accDAO.findAll();
		return "success";
	}
	
	public String execute() {
		
		ConcreteAccDAO accDAO2 = (ConcreteAccDAO) ConcreteDaoFactory.getInstance().createDAO("userDAO");
		
		Account userAcc = new Account();
		
		userAcc.setUsername(username);
		userAcc.setPassword(password);
		userAcc.setDepartment(department);
		userAcc.setEmail(email);
		userAcc.setPrivilege(privilege);
		userAcc.setRole(role);
		userAcc.setTime(time);
		autoGenerateUserID();
		if(userID==0)autoGenerateUserID();
		userAcc.setUserID(userID);
		
		userAcc.setFullname(null);
		String enabled = "YES";
		userAcc.setEnabled(enabled);

		//System.out.println("=w=w=w=w=w=w=w=w=w=w=w=w=w=w=w");
		Account account = accDAO2.findByName(username);
		
		if (account != null){
			accDAO2.delete(account);
			//accDAO2.save(userAcc);
		}
		accDAO2.save(userAcc);
		//System.out.println("mimimimimimimimimimimimimimimimimimi");
		alluser = accDAO2.findAll();
		return "success";
	}
	public void autoGenerateUserID(){
		if(userID<999999999 && userID >= 0){
			for(int i = 0; i<userList.size();i++){
				int chk = userList.get(i).getUserID();
				for(int j = 0;j<userList.size();j++){
					if(userID == 0){
						userID = userID+1;
					}
					if(userID == chk){
						userID = userID+1;
					}
				}
			}
		}
		else {
			System.out.println("userID can't more than 999999999");
			userID=0;
		}
	}

	public Account getAccSelect() {
		return accSelect;
	}

	public List<Account> getAlluser() {
		return alluser;
	}
	
	public String getDepartment(){
		return department;
	}
	
	public List<Departmentitem> getDepartmentitem() {
		return departmentitem;
	}

	public String getEmail(){
		return email;
	}
	
		public String getEnabled(){
			return enabled;
		}
		
		public String getPassword(){
			return password;
		}
		
		public String getPrivilage(){
			return privilege;
		}
	
		public String getPrivilege() {
			return privilege;
		}

		public List<Privilegeitem> getPrivilegeitem() {
			return privilegeitem;
		}
		
		public String getRole(){
			return role;
		}
		
		
		public String getTime(){
			return time;
		}
		
		public int getUserID(){
			return userID;
		}
		
		public List<Account> getUserList() {
			return userList;
		}
		
		public String getUsername(){
			return username;
		}
		

		public void setAccSelect(Account accSelect) {
			this.accSelect = accSelect;
		}

		public void setAlluser(List<Account> alluser) {
			this.alluser = alluser;
		}

		public void setDepartment(String department){
			this.department = department;
		}
		
		public void setDepartmentitem(List<Departmentitem> departmentitem) {
			this.departmentitem = departmentitem;
		}
		
		public void setEmail(String email){
			this.email = email;
		}
		
		public void setEnabled(){
			this.enabled = enabled;
		}
		
		public void setPassword(String password){
			this.password = password;
		}
		
		public void setPrivilege(String privilege){
			this.privilege = privilege;
		}
		
		public void setPrivilegeitem(List<Privilegeitem> privilegeitem) {
			this.privilegeitem = privilegeitem;
		}
		
		public void setRole(String role){
			this.role = role;
		}
		
		
		public void setTime(String time){
			this.time = time;
		}
		
		public void setUserID(int userID){
			this.userID = userID;
		}

		public void setUserList(List<Account> userList) {
			this.userList = userList;
		}

		public void setUsername(String username){
			this.username = username;
		}
}

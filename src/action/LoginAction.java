package action;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import model.Account;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import dao.ConcreteAccDAO;
import dao.ConcreteDaoFactory;

public class LoginAction implements SessionAware {

	private static final Logger LOG = LoggerFactory.getLogger(LoginAction.class);
	private Account account;
	private InetAddress addr;

	private String password;

	private SessionMap<String, Object> sessionMap;
	private Map taskMap;
	private String username;
	/**
	 * The action method
	 * 
	 * @return name of view
	 */
	public String authenticate() {

		ConcreteAccDAO userDAO = (ConcreteAccDAO) ConcreteDaoFactory.getInstance().createDAO("userDAO");

		if (userDAO.find(this.username, this.password) == null) {
			return "error";
		} else {
			account = userDAO.find(this.username, this.password);
			sessionMap.put("login", true);
			sessionMap.put("account", account);
			sessionMap.put("name", account.getFullname());
			AddTimeSheetAction link2 = new AddTimeSheetAction();
			ProjectTaskAction link = new ProjectTaskAction();
			taskMap = link.getTaskMap();

		}
		// taskMap = new HashMap();
		//
		// taskMap.put("Java", new ArrayList<String>(Arrays.asList("Spring",
		// "Hibernate", "Struts 2")));
		// taskMap.put(".Net", new ArrayList<String>(Arrays.asList("VB.Net",
		// "C#")));
		// taskMap.put("JavaScript", new
		// ArrayList<String>(Arrays.asList("jQuery")));

		return "success";
	}

	public Account getAccount() {
		return account;
	}

	// if (this.username.equals("user0") && this.password.equals("hello"))
	// return "success";
	// else
	// return "error";

	public String getPassword() {
		return password;
	}
	
	public Map getTaskMap() {
		return taskMap;
	}

	public String getUsername() {
		return username;
	}

	public String logOut() {
		sessionMap.clear();
		return "success";
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap) map;
	}

	public void setTaskMap(Map taskMap) {
		this.taskMap = taskMap;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String taskLink() {
		ProjectTaskAction link = new ProjectTaskAction();
		taskMap = link.getTaskMap();
		return "success";
	}

}

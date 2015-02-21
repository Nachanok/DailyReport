package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import model.Account;
import model.Privatetasklist;
import model.PrivatetasklistPK;
import model.Projecttask;
import model.Projectversion;

import org.apache.struts2.ServletActionContext;

import dao.ConcreteDaoFactory;
import dao.ConcretePrivateTaskListDAO;
import dao.ConcreteProjectVersionDAO;
import dao.ConcreteTaskListDAO;

public class ProjectTaskAction {

	private Account account;
	private List<Projectversion> projectVersionList;
	private Map taskMap;
	private List<Projectversion> projectLink = new ArrayList<Projectversion>();
	private List<String> projectLink2 = new ArrayList<String>();
	private ConcreteTaskListDAO taskDAO;
	private ConcreteProjectVersionDAO projectVersionDAO;
	private ConcretePrivateTaskListDAO privateTaskListDAO;
	private String project;
	private String task;

	public ProjectTaskAction() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		account = (Account) session.getAttribute("account");
		taskDAO = (ConcreteTaskListDAO) ConcreteDaoFactory.getInstance().createDAO("taskDAO");
		projectVersionDAO = (ConcreteProjectVersionDAO) ConcreteDaoFactory.getInstance().createDAO("projectVersionDAO");
		taskMap = new HashMap();
		Projectversion selected;
		projectVersionList = projectVersionDAO.findAll();
		for (int i = 0; i < projectVersionList.size(); i++) {
			selected = projectVersionList.get(i);
			String projectName = selected.getProject().getProjectName();
			projectLink.add(selected);
			String version = selected.getId().getVersionCode();
			String projectCode = selected.getId().getProjectCode();
			List<Projecttask> tasks = taskDAO.findByProjectCode(projectCode, version);
			List<String> tasksNames = new ArrayList<String>();
			for (int j = 0; j < tasks.size(); j++) {
				tasksNames.add(tasks.get(j).getTaskcode().getId().getTaskGroupCode() + "."
						+ tasks.get(j).getTaskcode().getId().getSubtaskCode());
			}
			String projectNameVer = projectName + "." + version;
			taskMap.put(projectNameVer, tasksNames);
			projectLink2.add(projectNameVer);
		}

	}

	public String disable() {
		privateTaskListDAO = (ConcretePrivateTaskListDAO) ConcreteDaoFactory.getInstance().createDAO(
				"privateTaskListDAO");
		Projecttask taskSelected = getProjectTask();
		// System.out.println(taskSelected.getId().getProjectCode() + "." +
		// taskSelected.getId().getVersionCode() + "."
		// + taskSelected.getId().getTaskGroupCode() + "." +
		// taskSelected.getId().getSubtaskCode());
		Privatetasklist toDelete = privateTaskListDAO.findByID(account.getUsername(), taskSelected.getId()
				.getProjectCode()
				+ "."
				+ taskSelected.getId().getVersionCode()
				+ "."
				+ taskSelected.getId().getTaskGroupCode() + "." + taskSelected.getId().getSubtaskCode());
		// System.out.println(toDelete.getAccount().getUsername());
		if (account.getPrivatetasklists().contains(toDelete)) {
			// System.out.println(toDelete.getAccount().getUsername());
			privateTaskListDAO.delete(toDelete);
			account.removePrivatetasklist(toDelete);
		}
		return "none";
	}

	public Projecttask getProjectTask() {
		Projectversion tempProject = projectLink.get(projectLink2.indexOf(project));
		String projectCode = tempProject.getId().getProjectCode();
		String projectVersion = tempProject.getId().getVersionCode();
		String taskName = task.substring(0, task.indexOf('.'));
		String taskgroup = task.substring(task.indexOf('.') + 1);
		Projecttask taskSelected = taskDAO.findByTaskCode(projectCode, projectVersion, taskName, taskgroup);

		return taskSelected;
	}

	public String display() {
		privateTaskListDAO = (ConcretePrivateTaskListDAO) ConcreteDaoFactory.getInstance().createDAO(
				"privateTaskListDAO");
		Projecttask taskSelected = getProjectTask();
		PrivatetasklistPK addToUser = new PrivatetasklistPK();
		addToUser.setUsername(account.getUsername());
		addToUser.setTaskCode(taskSelected.getId().getProjectCode() + "." + taskSelected.getId().getVersionCode() + "."
				+ taskSelected.getId().getTaskGroupCode() + "." + taskSelected.getId().getSubtaskCode());
		Privatetasklist addList = new Privatetasklist();
		addList.setId(addToUser);
		addList.setDescriptions(taskSelected.getProjectTaskDes());
		if (account.getPrivatetasklists().contains(addList)) {
			return "none";
		} else {
			account.addPrivatetasklist(addList);
			privateTaskListDAO.save(addList);
			// account.addPrivatetasklist(addList);
		}
		return "none";
	}

	public String execute() {
		return "success";
	}

	public Account getAccount() {
		return account;
	}

	public List<Projectversion> getProjectVersionList() {
		return projectVersionList;
	}

	public Map getTaskMap() {
		return taskMap;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setProjectVersionList(List<Projectversion> projectVersionList) {
		this.projectVersionList = projectVersionList;
	}

	public void setTaskMap(Map taskMap) {
		this.taskMap = taskMap;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
}

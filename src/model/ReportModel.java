package model;

import java.util.ArrayList;
import java.util.List;

public class ReportModel {

	private List<Usertimesheet> timesheets;
	private String projectName;
	private String projectVersion;
	private List<List<String>> descriptionLists;
	private int[] hArray;
	private List<String> task;

	public ReportModel() {
		timesheets = new ArrayList<Usertimesheet>();
		descriptionLists = new ArrayList<List<String>>();
		task = new ArrayList<String>();
	}

	public int[] gethArray() {
		return hArray;
	}

	public void sethArray(int[] hArray) {
		this.hArray = hArray;
	}

	public List<Usertimesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(List<Usertimesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public int totalHour() {
		int hour = 0;
		for (int i = 0; i < timesheets.size(); i++) {
			hour += timesheets.get(i).getUsageTime();
		}
		return hour;
	}

	public boolean addTimesheet(Usertimesheet u) {
		if (u.getId().getProjectCode().equalsIgnoreCase(projectName)
				&& u.getId().getVersionCode().equalsIgnoreCase(projectVersion)) {
			timesheets.add(u);
			return true;
		}
		return false;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	public List<String> getTotalTask() {
		List<String> lists = new ArrayList<String>();
		for (int i = 0; i < timesheets.size(); i++) {
			String s = timesheets.get(i).getId().getTaskGroupCode() + timesheets.get(i).getId().getSubtaskCode();
			if (!lists.contains(s)) {
				lists.add(s);
			}
		}
		task = lists;
		return lists;
	}

	public List<List<String>> getDescriptionLists() {
		return descriptionLists;
	}

	public void setDescriptionLists(List<List<String>> descriptionLists) {
		this.descriptionLists = descriptionLists;
	}

	public List<String> getTask() {
		return task;
	}

	public void setTask(List<String> task) {
		this.task = task;
	}

	public void arrange() {
		String s = projectName + " " + projectVersion + " (" + totalHour() + ")\n";
		List<String> tasks = getTotalTask();
		hArray = new int[tasks.size()];
		for (int i = 0; i < tasks.size(); i++) {
			List<String> empty = new ArrayList<String>();
			descriptionLists.add(empty);
		}
		for (int i = 0; i < timesheets.size(); i++) {
			String t = timesheets.get(i).getId().getTaskGroupCode() + timesheets.get(i).getId().getSubtaskCode();
			for (int j = 0; j < tasks.size(); j++) {
				if (t.equals(tasks.get(j))) {
					hArray[j] += timesheets.get(i).getUsageTime();
					if (!descriptionLists.get(j).contains(timesheets.get(i).getDescriptions()))
						descriptionLists.get(j).add(timesheets.get(i).getDescriptions());
				}
			}
		}
		for (int i = 0; i < task.size(); i++) {
			String temp = task.get(i) + " (" + hArray[i] + ")";
			task.set(i, temp);
		}
	}
}

package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.Account;
import model.Projecttask;
import model.Usertimesheet;
import model.UsertimesheetPK;

import org.apache.struts2.ServletActionContext;

import dao.ConcreteDaoFactory;
import dao.ConcreteProjectDAO;
import dao.ConcreteTaskListDAO;
import dao.ConcreteTimeSheetDAO;

public class AddTimeSheetAction {
	private Account account;
	private String amount;
	private String date;
	private String description;
	private List<List<Usertimesheet>> listsbydate;
	private String taskcode;
	private ConcreteTaskListDAO taskDAO;
	private ConcreteTimeSheetDAO timesheetDAO;
	private float totalday[] = new float[7];
	private String[] weekdate = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	public AddTimeSheetAction() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		timesheetDAO = (ConcreteTimeSheetDAO) ConcreteDaoFactory.getInstance().createDAO("timesheetDAO");
		taskDAO = (ConcreteTaskListDAO) ConcreteDaoFactory.getInstance().createDAO("taskDAO");
		account = (Account) session.getAttribute("account");
		List<Usertimesheet> timesheets = timesheetDAO.findByUser(account.getUsername());
		listsbydate = new ArrayList<List<Usertimesheet>>();
		session.setAttribute("timesheet", timesheets);
	}

	public String configDate(String date) {
		String[] ddMMyyyy = date.split("/");
		String concat = ddMMyyyy[2] + ddMMyyyy[0] + ddMMyyyy[1];
		// int answer = Integer.parseInt(concat);
		return concat;
	}

	public String execute() throws ParseException {

		ConcreteProjectDAO projectDAO = (ConcreteProjectDAO) ConcreteDaoFactory.getInstance().createDAO("projectDAO");

		int time;

		try {
			time = Integer.parseInt(amount);
		} catch (NumberFormatException a) {
			System.out.println("amount can only be a number");
			return "error";
		}
		UsertimesheetPK timesheetPK = new UsertimesheetPK();

		// System.out.println(amount);
		// System.out.println(date);
		// System.out.println(description);
		// System.out.println(taskcode);

		String[] splitter = taskcode.split("\\.");
		// System.out.println(splitter.length);
		Projecttask task = taskDAO.findByTaskCode(splitter[0], splitter[1], splitter[2], splitter[3]);
		timesheetPK.setProjectCode(splitter[0]);
		timesheetPK.setVersionCode(splitter[1]);
		timesheetPK.setTaskGroupCode(splitter[2]);
		timesheetPK.setSubtaskCode(splitter[3]);
		String customer = projectDAO.findByProjectCode(splitter[0]).getCustomer().getCustomerCode();
		timesheetPK.setUsername(account.getUsername());
		timesheetPK.setCustomerCode(customer);
		timesheetPK.setDate(configDate(date));
		Usertimesheet timesheet = new Usertimesheet();
		timesheet.setId(timesheetPK);
		timesheet.setDescriptions(description);
		timesheet.setUsageTime(time);
		timesheet.setAccount(account);
		timesheet.setProjecttask(task);
		timesheet.setApproved(0);
		// System.out.println(111111111);
		Usertimesheet checkIfExist = null;
		try {
			checkIfExist = timesheetDAO.findByAllPrimary(account.getUsername(), configDate(date), splitter[0],
					splitter[1], splitter[2], splitter[3]);
		} catch (javax.persistence.NoResultException e) {
			e.printStackTrace();
		}

		// System.out.println(2);
		if (checkIfExist != null) {
			// System.out.println("im here");
			timesheetDAO.delete(checkIfExist);
			account.removeUsertimesheet(checkIfExist);
			timesheetDAO.save(timesheet);
			account.addUsertimesheet(timesheet);
			// System.out.println("work");
		} else {
			timesheetDAO.save(timesheet);
			account.addUsertimesheet(timesheet);
		}
		show();
		// listsbydate = account.timesheetInWeek(date);
		// System.out.println("dai leaw");
		return "success";
	}

	public Account getAccount() {
		return account;
	}

	public String getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public List<List<Usertimesheet>> getListsbydate() {
		return listsbydate;
	}

	public String getTaskcode() {
		return taskcode;
	}

	public float[] getTotalday() {
		return totalday;
	}

	public String[] getWeekdate() {
		return weekdate;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setListsbydate(List<List<Usertimesheet>> listsbydate) {
		this.listsbydate = listsbydate;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public void setTotalday(float[] totalday) {
		this.totalday = totalday;
	}

	public void setWeekdate(String[] weekdate) {
		this.weekdate = weekdate;
	}

	public String show() throws ParseException {
		// System.out.println(date);
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("EEE(dd)");
		Date given = format.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(given);
		int delta = -c.get(GregorianCalendar.DAY_OF_WEEK) + 1;
		c.add(Calendar.DATE, delta);
		for (int i = 0; i < weekdate.length; i++) {
			weekdate[i] = format2.format(c.getTime());
			c.add(Calendar.DATE, 1);
		}
		System.out.println(weekdate.length);
		listsbydate = account.timesheetInWeek(date);
		for (int i = 0; i < listsbydate.size(); i++) {
			for (int j = 0; j < listsbydate.get(i).size(); j++) {
				totalday[j] += listsbydate.get(i).get(j).getUsageTime();
			}
		}
		// System.out.println(listsbydate.size());
		return "none";
	}
}

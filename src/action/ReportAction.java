package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import model.Account;
import model.ReportModel;
import model.Usertimesheet;

import org.apache.struts2.ServletActionContext;

import dao.ConcreteDaoFactory;
import dao.ConcreteTimeSheetDAO;

public class ReportAction {

	private Account account;
	private int choice;
	private String[] dates;
	private SimpleDateFormat dbFormat = new SimpleDateFormat("yyyyMMdd");
	private long different;
	private String enddate;
	private int[] hours;
	private SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
	private SimpleDateFormat individualFormat = new SimpleDateFormat("MM/dd/yyyy EEE");
	private String nextWeek;
	private String[] nextWeekLists;
	private List<ReportModel> reports;
	private List<List<Usertimesheet>> listsForIndividual;

	private String startdate;

	private Map taskMap;

	private List<Usertimesheet> timesheet;

	private ConcreteTimeSheetDAO timesheetDAO;

	public ReportAction() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		timesheetDAO = (ConcreteTimeSheetDAO) ConcreteDaoFactory.getInstance().createDAO("timesheetDAO");
		account = (Account) session.getAttribute("account");

	}

	public String dailyHour() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();
		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		different = TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
		hours = new int[(int) different + 1];
		dates = new String[(int) different + 1];
		for (int i = 0; i < timesheet.size(); i++) {

			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if ((date.after(start) && date.before(end)) || date.equals(end)) {
				temporary.add(timesheet.get(i));
			}

		}

		for (int i = 0; i < temporary.size(); i++) {
			Date date = dbFormat.parse(temporary.get(i).getId().getDate());
			int shift = (int) (TimeUnit.DAYS.convert(date.getTime() - start.getTime(), TimeUnit.MILLISECONDS));
			hours[shift] += temporary.get(i).getUsageTime();
			dates[shift] = temporary.get(i).getId().getDate();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		for (int i = 0; i < dates.length; i++) {
			dates[i] = myFormat.format(c.getTime());
			c.add(Calendar.DATE, 1);
		}

		return "success";
	}

	public String execute() throws ParseException {
		if (choice == 1) {
			return dailyHour();
		}
		if (choice == 2) {
			return individualReport();
		}
		if (choice == 3) {
			return monthlyHour();
		}
		if (choice == 4) {
			return yearlyHour();
		}
		return "error";
	}

	public void genDate(int type) {

	}

	public Account getAccount() {
		return account;
	}

	public int getChoice() {
		return choice;
	}

	public String[] getDates() {
		return dates;
	}

	public long getDifferent() {
		return different;
	}

	public String getEnddate() {
		return enddate;
	}

	public int[] getHours() {
		return hours;
	}

	public String getNextWeek() {
		return nextWeek;
	}

	public List<ReportModel> getReports() {
		return reports;
	}

	public String getStartdate() {
		return startdate;
	}

	public Map getTaskMap() {
		return taskMap;
	}

	public List<Usertimesheet> getTimesheet() {
		return timesheet;
	}

	public String linkToPrivatelist() {
		ProjectTaskAction link = new ProjectTaskAction();
		taskMap = link.getTaskMap();
		return "success";
	}

	public String monthlyHour() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();

		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(start);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(end);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		different = (long) (diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
		hours = new int[(int) different + 1];
		dates = new String[(int) different + 1];
		for (int i = 0; i < timesheet.size(); i++) {
			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if (date.after(start) && date.before(end)) {
				temporary.add(timesheet.get(i));
			}
		}

		for (int i = 0; i < temporary.size(); i++) {
			Date date = dbFormat.parse(temporary.get(i).getId().getDate());
			Calendar dateCalendar = new GregorianCalendar();
			dateCalendar.setTime(date);
			int diffYearShift = dateCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int shift = (diffYearShift * 12 + dateCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH));
			hours[shift] += temporary.get(i).getUsageTime();
			dates[shift] = temporary.get(i).getId().getDate();

		}

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		for (int i = 0; i < dates.length; i++) {
			dates[i] = myFormat.format(c.getTime());
			c.add(Calendar.MONTH, 1);
		}

		return "success";
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public void setDates(String[] dates) {
		this.dates = dates;
	}

	public void setDifferent(long different) {
		this.different = different;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}

	public void setNextWeek(String nextWeek) {
		this.nextWeek = nextWeek;
	}

	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setTaskMap(Map taskMap) {
		this.taskMap = taskMap;
	}

	public void setTimesheet(List<Usertimesheet> timesheet) {
		this.timesheet = timesheet;
	}

	public List<ReportModel> tasks() {
		List<ReportModel> lists = new ArrayList<ReportModel>();
		for (int i = 0; i < timesheet.size(); i++) {
			boolean checker = false;
			for (int j = 0; j < lists.size(); j++) {
				checker = lists.get(j).addTimesheet(timesheet.get(i));
				if (checker)
					break;
			}
			if (!checker) {
				ReportModel model = new ReportModel();
				model.setProjectName(timesheet.get(i).getId().getProjectCode());
				model.setProjectVersion(timesheet.get(i).getId().getVersionCode());
				model.addTimesheet(timesheet.get(i));
				lists.add(model);
			}
		}
		return lists;

	}

	public List<List<Usertimesheet>> getListsForIndividual() {
		return listsForIndividual;
	}

	public void setListsForIndividual(List<List<Usertimesheet>> listsForIndividual) {
		this.listsForIndividual = listsForIndividual;
	}

	public String weeklyHour() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();

		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		long day = TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS);
		different = (long) Math.ceil(day / 7.0);
		hours = new int[(int) different];
		dates = new String[(int) different];
		for (int i = 0; i < timesheet.size(); i++) {

			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if (date.after(start) && date.before(end)) {
				temporary.add(timesheet.get(i));
			}

		}

		for (int i = 0; i < temporary.size(); i++) {
			Date date = dbFormat.parse(temporary.get(i).getId().getDate());
			int shift = (int) (TimeUnit.DAYS.convert(date.getTime() - start.getTime(), TimeUnit.MILLISECONDS) / 7);
			hours[shift] += temporary.get(i).getUsageTime();
			dates[shift] = temporary.get(i).getId().getDate();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		for (int i = 0; i < dates.length; i++) {
			dates[i] = myFormat.format(c.getTime());
			c.add(Calendar.DATE, 7);
		}

		return "success";
	}

	public String weeklyReport() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();
		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		different = TimeUnit.DAYS.convert(end.getTime() - start.getTime(), TimeUnit.MILLISECONDS);

		for (int i = 0; i < timesheet.size(); i++) {

			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if ((date.after(start) || date.equals(start)) && (date.before(end) || date.equals(end))) {
				temporary.add(timesheet.get(i));
			}

		}
		timesheet = temporary;
		reports = tasks();
		for (int i = 0; i < reports.size(); i++) {
			reports.get(i).arrange();
		}
		return "success";
	}

	public String[] getNextWeekLists() {
		return nextWeekLists;
	}

	public void setNextWeekLists(String[] nextWeekLists) {
		this.nextWeekLists = nextWeekLists;
	}

	public String yearlyHour() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();
		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(start);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(end);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR) + 1;
		different = (long) diffYear;
		hours = new int[diffYear];
		dates = new String[diffYear];
		for (int i = 0; i < timesheet.size(); i++) {
			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if (date.after(start) && date.before(end)) {
				temporary.add(timesheet.get(i));
			}
		}

		for (int i = 0; i < temporary.size(); i++) {
			Date date = dbFormat.parse(temporary.get(i).getId().getDate());
			Calendar dateCalendar = new GregorianCalendar();
			dateCalendar.setTime(date);
			int diffYearShift = dateCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			hours[diffYearShift] += temporary.get(i).getUsageTime();
			dates[diffYearShift] = temporary.get(i).getId().getDate();

		}

		Calendar c = Calendar.getInstance();
		c.setTime(start);
		for (int i = 0; i < dates.length; i++) {
			dates[i] = myFormat.format(c.getTime());
			c.add(Calendar.YEAR, 1);
		}
		return "success";
	}

	public String individualReport() throws ParseException {
		timesheet = timesheetDAO.findByUser(account.getUsername());
		listsForIndividual = new ArrayList<List<Usertimesheet>>();

		List<Usertimesheet> temporary = timesheetDAO.findByUser(account.getUsername());
		temporary.clear();
		Date start = myFormat.parse(startdate);
		Date end = myFormat.parse(enddate);
		for (int i = 0; i < timesheet.size(); i++) {

			String timesheetdate = timesheet.get(i).getId().getDate();
			Date date = dbFormat.parse(timesheetdate);
			if (((date.after(start) || date.equals(start)) && (date.before(end)) || date.equals(end))) {
				temporary.add(timesheet.get(i));
			}
		}
		List<Usertimesheet> groupbydate = new ArrayList<Usertimesheet>();
		// System.out.println(temporary.size());
		for (int i = 0; i < temporary.size(); i++) {
			if (i > 0 && temporary.get(i).getId().getDate().equals(temporary.get(i - 1).getId().getDate())) {
				groupbydate.add(temporary.get(i));
				// System.out.println(groupbydate.size());
				if (i == temporary.size() - 1) {
					listsForIndividual.add(groupbydate);
					groupbydate = new ArrayList<Usertimesheet>();
				}
			} else {
				if (i > 0) {
					listsForIndividual.add(groupbydate);
					groupbydate = new ArrayList<Usertimesheet>();
				}
				groupbydate.add(temporary.get(i));
				// System.out.println(groupbydate.size());
			}
		}

		dates = new String[listsForIndividual.size()];
		for (int i = 0; i < dates.length; i++) {
			Date date = dbFormat.parse(listsForIndividual.get(i).get(0).getId().getDate());
			dates[i] = individualFormat.format(date);
		}
		return "individual";
	}

}

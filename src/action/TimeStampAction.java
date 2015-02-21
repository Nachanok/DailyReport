package action;

import java.util.Date;

import javax.servlet.http.HttpSession;

import model.Account;

import org.apache.struts2.ServletActionContext;

import dao.ConcreteDaoFactory;
import dao.ConcreteTimestampDAO;

public class TimeStampAction {

	private ConcreteTimestampDAO timestampDAO;
	private Account account;
	private Date start;
	private Date end;

	public TimeStampAction() {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		timestampDAO = (ConcreteTimestampDAO) ConcreteDaoFactory.getInstance().createDAO("timestampDAO");
		account = (Account) session.getAttribute("account");
	}

	public String userAction() {
		return null;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}

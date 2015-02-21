package model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the privatetasklist database table.
 * 
 */
@Entity
@NamedQuery(name = "Privatetasklist.findAll", query = "SELECT p FROM Privatetasklist p")
public class Privatetasklist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrivatetasklistPK id;

	private int deliverableCount;

	@Lob
	private String deliverableDes;

	@Lob
	private String descriptions;

	private int enable;

	private float usageTime;

	// bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name = "Username")
	private Account account;

	public Privatetasklist() {
	}

	public PrivatetasklistPK getId() {
		return this.id;
	}

	public void setId(PrivatetasklistPK id) {
		this.id = id;
	}

	public int getDeliverableCount() {
		return this.deliverableCount;
	}

	public void setDeliverableCount(int deliverableCount) {
		this.deliverableCount = deliverableCount;
	}

	public String getDeliverableDes() {
		return this.deliverableDes;
	}

	public void setDeliverableDes(String deliverableDes) {
		this.deliverableDes = deliverableDes;
	}

	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getEnable() {
		return this.enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public float getUsageTime() {
		return this.usageTime;
	}

	public void setUsageTime(float usageTime) {
		this.usageTime = usageTime;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Privatetasklist)) {
			return false;
		}
		Privatetasklist castOther = (Privatetasklist) other;
		return this.id.equals(castOther.getId());
	}

}
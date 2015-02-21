package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Usertimesheet;

public class ConcreteTimeSheetDAO implements DAO {

	private EntityManager em;

	public ConcreteTimeSheetDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		Usertimesheet task = (Usertimesheet) o;
		Usertimesheet tmp = em.find(Usertimesheet.class, task.getId());
		em.getTransaction().begin();
		em.remove(tmp);
		em.getTransaction().commit();

	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Usertimesheet) o);
		em.getTransaction().commit();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	public List<Usertimesheet> findByUser(String username) {
		Query query = em.createQuery("SELECT u FROM Usertimesheet u WHERE u.id.username = :id");
		query.setParameter("id", username);
		List<Usertimesheet> listTimesheet = query.getResultList();
		return listTimesheet;
	}

	public Usertimesheet findByAllPrimary(String username, String date, String projectCode, String versionCode,
			String taskCode, String subtaskCode) {
		System.out.println(username + date + projectCode + versionCode + taskCode + subtaskCode);
		Query query = em
				.createQuery("SELECT u FROM Usertimesheet u WHERE u.id.username = :id AND u.id.date = :date AND u.id.projectCode = :projectCode AND u.id.versionCode = :versionCode And u.id.taskGroupCode = :taskCode AND u.id.subtaskCode = :subtaskCode");
		query.setParameter("id", username);
		query.setParameter("date", date);
		query.setParameter("projectCode", projectCode);
		query.setParameter("versionCode", versionCode);
		query.setParameter("taskCode", taskCode);
		query.setParameter("subtaskCode", subtaskCode);
		Usertimesheet timesheet = (Usertimesheet) query.getSingleResult();
		return timesheet;
	}
}

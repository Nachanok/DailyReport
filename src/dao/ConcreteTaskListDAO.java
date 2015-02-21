package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Projecttask;

public class ConcreteTaskListDAO implements DAO {

	private EntityManager em;

	public ConcreteTaskListDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Projecttask) o);
		em.getTransaction().commit();

	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
	}

	// NEED FIXED
	public List<Projecttask> findByProjectCode(String code, String code2) {
		// TODO Auto-generated method stub

		Query query = em
				.createQuery("select u from Projecttask u where u.projectversion.id.projectCode = :id AND u.projectversion.id.versionCode = :id2");
		query.setParameter("id", code);
		query.setParameter("id2", code2);
		List<Projecttask> listTask = query.getResultList();
		return listTask;
	}

	public Projecttask findByTaskCode(String code1, String code2, String code3, String code4) {
		Query query = em
				.createQuery("select u from Projecttask u where u.projectversion.id.projectCode = :id1 AND u.projectversion.id.versionCode = :id2 AND u.taskcode.id.taskGroupCode = :id3 AND u.taskcode.id.subtaskCode = :id4");
		query.setParameter("id1", code1);
		query.setParameter("id2", code2);
		query.setParameter("id3", code3);
		query.setParameter("id4", code4);
		List<Projecttask> listTask = query.getResultList();
		if (listTask.size() != 1)
			return null;
		return listTask.get(0);
	}
}

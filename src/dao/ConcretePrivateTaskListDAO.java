package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Privatetasklist;

public class ConcretePrivateTaskListDAO implements DAO {

	private EntityManager em;

	public ConcretePrivateTaskListDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Privatetasklist) o);
		em.getTransaction().commit();

	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		Privatetasklist task = (Privatetasklist) o;
		Privatetasklist tmp = em.find(Privatetasklist.class, task.getId());
		em.getTransaction().begin();
		em.remove(tmp);
		em.getTransaction().commit();

	}

	public List<Privatetasklist> findByUser(String username) {
		Query query = em.createQuery("select u from Privatetasklist u where u.id.username = :id");
		query.setParameter("id", username);
		List<Privatetasklist> listUser = query.getResultList();
		return listUser;

	}

	public Privatetasklist findByID(String username, String taskcode) {
		Query query = em
				.createQuery("select u from Privatetasklist u where u.id.username = :id AND u.id.taskCode = :id2");
		query.setParameter("id", username);
		query.setParameter("id2", taskcode);
		Privatetasklist tasklist = (Privatetasklist) query.getSingleResult();
		return tasklist;

	}

}

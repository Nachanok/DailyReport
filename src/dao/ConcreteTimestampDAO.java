package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Timestamp;

public class ConcreteTimestampDAO implements DAO {

	private EntityManager em;

	public ConcreteTimestampDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Timestamp) o);
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

	public List<Timestamp> findById(int id) {
		Query query = em.createQuery("select u from Timestamp u where u.ID.id = :id");
		query.setParameter("id", id);
		List<Timestamp> listTimestamp = query.getResultList();
		return listTimestamp;
	}

}

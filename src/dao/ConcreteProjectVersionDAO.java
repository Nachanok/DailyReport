package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Projectversion;

public class ConcreteProjectVersionDAO implements DAO {

	private EntityManager em;

	public ConcreteProjectVersionDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub

	}

	public List<Projectversion> findAll() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT q FROM Projectversion q");
		List<Projectversion> projectVersions = query.getResultList();
		if (projectVersions.size() > 0)
			return projectVersions;
		return null;
	}

}

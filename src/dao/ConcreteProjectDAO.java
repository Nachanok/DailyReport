package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Project;

public class ConcreteProjectDAO implements DAO {

	private EntityManager em;

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Project) o);
		em.getTransaction().commit();

	}

	public ConcreteProjectDAO(EntityManager em) {
		// TODO Auto-generated constructor stub
		this.em = em;
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub

	}

	public List<Project> findAll() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT q FROM Project q");
		List<Project> projects = query.getResultList();
		if (projects.size() != 0)
			return projects;
		projects = new ArrayList<Project>();
		return projects;
	}

	public Project findByProjectCode(String code) {
		Query query = em.createQuery("SELECT q FROM Project q where q.projectCode= :code");
		query.setParameter("code", code);
		Project project = (Project) query.getSingleResult();
		return project;
	}

}

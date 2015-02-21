package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Account;
import model.Departmentitem;
import model.Privilegeitem;

public class ConcreteDepartmentitemDAO implements DAO{

	private EntityManager em;

	public ConcreteDepartmentitemDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Departmentitem) o);
		em.getTransaction().commit();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		Departmentitem item = (Departmentitem) o;
		Departmentitem tmp = em.find(Departmentitem.class, item.getName());
		em.getTransaction().begin();
		em.remove(tmp);
		em.getTransaction().commit();
	}
	
	public List<Departmentitem> findByName(String name) {
		Query query = em.createQuery("select u from Departmentitem u where u.id.username = :id");
		query.setParameter("id", name);
		List<Departmentitem> item = query.getResultList();
		return item;

	}
	
	public List<Departmentitem> findAll() {
		Query query = em.createQuery("SELECT q FROM Departmentitem q");
		List<Departmentitem> item = query.getResultList();
		return item;
	}
}

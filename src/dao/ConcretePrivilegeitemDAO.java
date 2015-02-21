package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Departmentitem;
import model.Privilegeitem;

public class ConcretePrivilegeitemDAO implements DAO{
	
	private EntityManager em;

	public ConcretePrivilegeitemDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Privilegeitem) o);
		em.getTransaction().commit();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		Privilegeitem item = (Privilegeitem) o;
		Privilegeitem tmp = em.find(Privilegeitem.class, item.getName());
		em.getTransaction().begin();
		em.remove(tmp);
		em.getTransaction().commit();
	}
	
	public List<Privilegeitem> findByName(String name) {
		Query query = em.createQuery("select u from Privilegeitem u where u.id.username = :id");
		query.setParameter("id", name);
		List<Privilegeitem> item = query.getResultList();
		return item;

	}

	public List<Privilegeitem> findAll() {
		Query query = em.createQuery("SELECT q FROM Privilegeitem q");
		List<Privilegeitem> item = query.getResultList();
		return item;
	}
}

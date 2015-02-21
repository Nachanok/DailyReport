package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Account;
import model.Privatetasklist;

public class ConcreteAccDAO implements DAO {

	private EntityManager em;

	public ConcreteAccDAO(EntityManager em) {
		this.em = em;
	}

	public List<Account> findAll() {
		Query query = em.createQuery("SELECT q FROM Account q");
		List<Account> users = query.getResultList();
//		System.out.println(users.size());
		return users;
	}

	public Account find(String id, String password) {
		Query query = em.createQuery("select u from Account u where u.username = :id and u.password = :password");
		query.setParameter("id", id);
		query.setParameter("password", password);
		List<Account> listUser = query.getResultList();
		if (listUser.size() != 0)
			return listUser.get(0);
		return null;
	}
	
	public Account findByName(String id){
		Query query = em.createQuery("select u from Account u where u.username = :id");
		query.setParameter("id", id);
		List<Account> listUser = query.getResultList();
		if (listUser.size() != 0)
			return listUser.get(0);
		return null;
	}

	@Override
	public void save(Object o) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		em.persist((Account) o);
		em.getTransaction().commit();
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object o) {
		Account user = (Account) o;
		System.out.println(user.getUsername());
		Account tmp = em.find(Account.class, user.getUsername());
		em.getTransaction().begin();
		em.remove(tmp);
		em.getTransaction().commit();

	}
}

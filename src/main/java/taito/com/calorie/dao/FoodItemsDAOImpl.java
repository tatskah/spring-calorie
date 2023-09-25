package taito.com.calorie.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Configuration;
import taito.com.calorie.model.FoodItems;

@Configuration
public class FoodItemsDAOImpl implements FoodItemsDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(FoodItems item) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(item);
		tx.commit();
		session.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodItems> list() {
		
		Session session = sessionFactory.openSession();
		List<FoodItems> fooditemsList = session.createQuery("from FoodItems fi ORDER BY fi.name").setMaxResults(24).list();
		
		session.close();
		
		return fooditemsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodItems> getAll() {
		
		Session session = sessionFactory.openSession();
		List<FoodItems> fooditemsList = session.createQuery("from FoodItems fi ORDER BY fi.name").setCacheable(true).list();
		
		session.close();
		
		return fooditemsList;
	}
	
	@Override
	public FoodItems getById(int id) {
		FoodItems item = null;
		Session session = sessionFactory.openSession();
		item = session.get(FoodItems.class, id);
		
		session.close();
		return item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FoodItems> getByName(String name) {
		List<FoodItems> item = null;
		
		Session session = sessionFactory.openSession();
		Query q = session.createQuery("from FoodItems f WHERE f.name LIKE ?1");
		q.setParameter(1, "%" + name + "%");
		item = q.getResultList();
		
		session.close();
		return item;
	}
	
	
	
	
	
	
	

}
	
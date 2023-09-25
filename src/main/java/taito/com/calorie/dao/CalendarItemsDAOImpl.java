package taito.com.calorie.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Configuration;

import taito.com.calorie.model.CalendarItems;

@Configuration
public class CalendarItemsDAOImpl implements CalendarItemsDAO {

	private SessionFactory sessionFactory;
	  
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(CalendarItems item) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(item);
		tx.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdate(CalendarItems item) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(item);
		tx.commit();
		session.close();
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarItems> list() {
		Session session = sessionFactory.openSession();
		List<CalendarItems> foodCalendarList = session.createQuery("from CalendarItems fc ORDER BY fc.add_date DESC").setMaxResults(16).list();
		session.close();
		
		return foodCalendarList;
	}

	
	@Override
	public CalendarItems getLastAdded() {
		Session session = sessionFactory.openSession();
		CalendarItems fc = (CalendarItems) session.createQuery("from FoodCalendar fc ORDER BY fc.add_date DESC").setMaxResults(1).uniqueResult();
		session.close();
		return fc;
	}
	
	@Override
	public CalendarItems getById(int id) {
		Session session = sessionFactory.openSession();
		CalendarItems item = (CalendarItems)session.get(CalendarItems.class,id);
		session.close();
		return item;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CalendarItems> getAllById(int id) {
		Session session = sessionFactory.openSession();
		List<CalendarItems> foodCalendarList = session.createQuery("from CalendarItems fc WHERE foodcalendar_id=" + id).list();
		session.close();
		
		return foodCalendarList;
	}
	
	@Override
	public void deleteById(CalendarItems item){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(item);
		tx.commit();
		session.close();
	}
	
}

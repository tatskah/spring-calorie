package taito.com.calorie.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Configuration;
import taito.com.calorie.model.FoodCalendar;
import taito.com.calorie.utils.ApplicationHelper;

@Configuration
public class FoodCalendarDAOImpl implements FoodCalendarDAO {

	private SessionFactory sessionFactory;
	
	@PersistenceContext
    private EntityManager em;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void save(FoodCalendar item) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(item);
		tx.commit();
		session.close();
		
	}

	@Override
	public void saveOrUpdate(FoodCalendar item) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(item);
		tx.commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FoodCalendar> list() {
		Session session = sessionFactory.openSession();
		List<FoodCalendar> foodCalendarList = session.createQuery("from FoodCalendar fc ORDER BY fc.add_date DESC").setMaxResults(16).list();
		session.close();
		
		return foodCalendarList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FoodCalendar> getCalendarItemsWithSumFoodItems() {

		Session session = sessionFactory.openSession();

		List<FoodCalendar> foodCalendarList = session.createQuery("from FoodCalendar fc ORDER BY fc.add_date DESC").setMaxResults(16).list();

		for (FoodCalendar item : foodCalendarList) {
			String sql = "SELECT SUM(fi.kcal), SUM(fi.kj), SUM(fi.fat), SUM(fi.protein), SUM(fi.piece), SUM(fi.gram) FROM FoodCalendar fc INNER JOIN fc.calendar_items AS fi WHERE fc.id="
					+ item.getId() + " GROUP by fc.id";

			Iterator<?> results = session.createQuery(sql).list().iterator();

			while (results.hasNext()) {	
				Object[] row = (Object[]) results.next();

				item.setKcal_total((Double) row[0]);
				item.setKj_total((Double) row[1]);
				item.setFat_total((Double) row[2]);
				item.setProtein_total((Double) row[3]);
				item.setPiece_total((Double) row[4]);
				item.setGram_total((Double) row[5]);
			}
		}
		session.close();

		return foodCalendarList;
	}
	
	@Override
	public List<String> getCalendarItemsWithCaloriesSum(TIME_PERIOD period) {
		Session session = sessionFactory.openSession();
		List<String> weekdata_ret = new ArrayList<>(); 
		List<LocalDate> weekDays = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateTimeFormatter date_formatter = null;
		
		switch(period) {
			case WEEK:
				weekDays = ApplicationHelper.getDaysOfCurrentWeek();
				date_formatter = DateTimeFormatter.ofPattern("EEEE dd.MM.yyyy");
			break;
			case MONTH:
				weekDays = ApplicationHelper.getDaysOfCurrentMonth();
				date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}
		
		
		for (LocalDate item : weekDays) {
			String sql = "SELECT SUM(fi.kcal), SUM(fi.kj), SUM(fi.fat), SUM(fi.protein) FROM FoodCalendar fc INNER JOIN fc.calendar_items AS fi WHERE fc.add_date BETWEEN : start AND :end GROUP by fc.id";
			

			
			LocalDateTime dateTime = LocalDateTime.parse(item.toString() + " 00:00", formatter);
			
			Query<?> results = session.createQuery(sql);
			results.setParameter("start",dateTime.minusDays(0));
			results.setParameter("end", dateTime.plusDays(1));
			
			Iterator<?> result = (Iterator<?>) results.list().iterator();
			while (result.hasNext()) {
				Object[] row = (Object[]) result.next();
				weekdata_ret.add(item.format(date_formatter).toString().toUpperCase() + "#" + String.format("%.2f",row[0]).replace(",", "."));
			}
			
		}
		session.close();
		return weekdata_ret;
	}
	
	@Override
	public FoodCalendar getLastAdded() {
		Session session = sessionFactory.openSession();
		FoodCalendar fc = (FoodCalendar) session.createQuery("from FoodCalendar fc ORDER BY fc.add_date DESC").setMaxResults(1).uniqueResult();
		session.close();
		return fc;
	}
	
	@Override
	public FoodCalendar getById(int id) {
		Session session = sessionFactory.openSession();
		FoodCalendar item = (FoodCalendar)session.get(FoodCalendar.class,id);
		session.close();
		return item;
	}

}

package taito.com.calorie.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import taito.com.calorie.model.CalendarItems;

/**
 * @author taitohaataja
 *
 */
@Transactional
@Repository
public interface CalendarItemsDAO {

	
	public void save(CalendarItems item);
	
	public List<CalendarItems> list();

	CalendarItems getLastAdded();

	CalendarItems getById(int id);

	void saveOrUpdate(CalendarItems item);

	List<CalendarItems> getAllById(int id);

	void deleteById(CalendarItems item);

	
	
}

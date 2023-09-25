package taito.com.calorie.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import taito.com.calorie.model.FoodCalendar;

/**
 * @author taitohaataja
 *
 */
@Transactional
@Repository
public interface FoodCalendarDAO {

	public static enum TIME_PERIOD{
		WEEK,
		MONTH		
	}
	
	public void save(FoodCalendar item);
	
	public List<FoodCalendar> list();

	FoodCalendar getLastAdded();

	FoodCalendar getById(int id);

	void saveOrUpdate(FoodCalendar item);

	List<FoodCalendar> getCalendarItemsWithSumFoodItems();

	List<String> getCalendarItemsWithCaloriesSum(TIME_PERIOD period);
	
	
}

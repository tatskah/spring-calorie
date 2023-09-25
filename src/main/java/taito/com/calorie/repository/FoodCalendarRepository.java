package taito.com.calorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taito.com.calorie.model.FoodCalendar;

public interface FoodCalendarRepository extends JpaRepository<FoodCalendar, Integer>{
	
}

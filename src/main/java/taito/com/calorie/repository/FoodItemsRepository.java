package taito.com.calorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taito.com.calorie.model.FoodItems;

public interface FoodItemsRepository extends JpaRepository<FoodItems, Integer>{

}

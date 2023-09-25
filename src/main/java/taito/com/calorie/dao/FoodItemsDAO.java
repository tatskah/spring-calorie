package taito.com.calorie.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import taito.com.calorie.model.FoodItems;

/**
 * @author taitohaataja
 *
 */
@Repository
public interface FoodItemsDAO {

	
	public void save(FoodItems item);
	
	public List<FoodItems> list();
	
	public List<FoodItems> getByName(String name);

	FoodItems getById(int id);

	List<FoodItems> getAll();
	
}

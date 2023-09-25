package taito.com.calorie.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import taito.com.calorie.dao.FoodCalendarDAO;
import taito.com.calorie.utils.ApplicationHelper;

@RestController
public class HighChartController {

    @GetMapping("/get-data/{month}")
    public ResponseEntity<Map<Integer, Map<LocalDate, Double>>> getPieChart(@PathVariable int month) {
    	Map<Integer, Map<LocalDate, Double>> allData = new HashMap<Integer, Map<LocalDate, Double>>();
    	Map<LocalDate, Double> graphData1 = new TreeMap<>();
        
        List<LocalDate> monthData = ApplicationHelper.getDaysOfCurrentMonth();
        FoodCalendarDAO dao = (FoodCalendarDAO)ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
        List<String> data = dao.getCalendarItemsWithCaloriesSum(FoodCalendarDAO.TIME_PERIOD.MONTH);
        Map<String, Double> calories_data = new TreeMap<>();
        
        String[] row = new String[data.size()];
        for(String item : data) {
        	row = item.split("#");
        	calories_data.put(row[0], Double.valueOf(row[1]));
        }
        
        for(LocalDate day : monthData) {
        	if(calories_data.containsKey(day.toString())) {
        		graphData1.put( day, calories_data.get(day.toString()));
        	}else {
        		graphData1.put( day, Double.valueOf(0));
        	}	
        }
    	allData.put(1, graphData1);

    	return new ResponseEntity<>(allData, HttpStatus.OK);
    }
}
package taito.com.calorie.controller;

import org.springframework.web.bind.annotation.*;
import taito.com.calorie.dao.CalendarItemsDAO;
import taito.com.calorie.dao.FoodCalendarDAO;
import taito.com.calorie.dao.FoodItemsDAO;
import taito.com.calorie.model.CalendarItems;
import taito.com.calorie.model.FoodCalendar;
import taito.com.calorie.model.FoodItems;
import taito.com.calorie.repository.FoodItemsRepository;
import taito.com.calorie.repository.FoodCalendarRepository;

import taito.com.calorie.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Controller
public class MainController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = false )
	FoodItemsRepository food_items_repository;

	@Autowired(required = false )
	FoodCalendarRepository food_calendar_repository;
	
	@RequestMapping("/")
	public String show_index(Model model) {
		log.info("root place");
		model.addAttribute("page_title", "Aloitus");
		this.initSidebarData(model);
		return "index";	
	}
	
	@RequestMapping("report")
	public String show_report(Model model) {
		
		model.addAttribute("page_title", "Raportti");
		this.initSidebarData(model);
		return "report";	
	}

	@RequestMapping("login")
	public String show_login(Model model) {
		
		model.addAttribute("page_title", "Kirjaudu");
		//this.initSidebarData(model);
		return "login";	
	}
	
	@RequestMapping("register")
	public String show_register(Model model) {
		
		model.addAttribute("page_title", "Rekisteröidy");
		this.initSidebarData(model);
		return "register";	
	}

//	FOOD ITEM
	@RequestMapping("food-items-search/")
	public String show_food_item_by_search2(Model model) {
		List<FoodItems> food_items = null;
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);
	
		food_items = foodItemDao.list();
		
		model.addAttribute("food_items",food_items);
		
		this.initSidebarData(model);
		return "fooditems";	
	}
	
	@RequestMapping("food-items-search/{name}")
	public String show_food_item_by_search(@PathVariable String name, Model model) {
		List<FoodItems> food_items = null;
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);
			
		food_items = foodItemDao.getByName(name);

		model.addAttribute("food_items",food_items);
		
		this.initSidebarData(model);
		return "fooditems";	
	}
	
	@RequestMapping("/food-items")
	public String getfood_items(Model model) {		
//		Optional<FoodItems> food_item = food_items_repository.findById(313);
	
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);	
		List<FoodItems> food_items = foodItemDao.list();
		
		model.addAttribute("food_items",food_items);
		model.addAttribute("page_title", "Ruoka-ainekset");
		this.initSidebarData(model);
		return "fooditems";
	}
	
	@RequestMapping("food-items/{id}")
	public String show_food_item(@PathVariable int id, Model model) {
		//food_items_repository.deleteById(id);
		FoodItems food_item = food_items_repository.getReferenceById(id);
		
		model.addAttribute("food_items",food_item);
		
		this.initSidebarData(model);
		return "fooditems";	
	}
	
	@RequestMapping("add-food-item")
	public String show_add_food_item(Model model) {
		FoodItems item = new FoodItems();
		
		model.addAttribute("food_item",item);
		model.addAttribute("show_updated",false);
		
		model.addAttribute("page_title", "Lisää aines");
		this.initSidebarData(model);
		return "add_fooditem";	
	}
	
	@PostMapping("save-new-food-item")
	public String saveNewFoodItem(@ModelAttribute("food-item") FoodItems item) {
//		item.setCreated_at(LocalDateTime.now());
	    food_items_repository.save(item);
	    return "redirect:/food-items/" + item.getId();	
	}

	@RequestMapping("edit-food-item/{id}")
	public String edit_food_item(@PathVariable int id, Model model) {
		
		FoodItems food_item = (FoodItems) food_items_repository.getReferenceById(id);
				
		model.addAttribute("page_title", "Muokkaa ainesta");
		model.addAttribute("food_item",food_item);
		
		this.initSidebarData(model);
		return "edit_fooditem";	
	}
	
	@PostMapping("save-old-food-item/{id}")
	public String saveOldFoodItem(@ModelAttribute("food-item") FoodItems item) {
	    food_items_repository.save(item);
	    return "redirect:/food-items";	
	}
	
	@RequestMapping("del-food-item/{id}")
	public String delete_food_item(@PathVariable int id, Model model) {
		
		food_items_repository.deleteById(id);
		
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);	
		List<FoodItems> food_items = foodItemDao.list();
		
		model.addAttribute("food_items",food_items);
		
		this.initSidebarData(model);
		return "fooditems";	
	}
	// END FOOD ITEMS FUNCTIONS

	
	/***
	 * CALENDAR FUNCTIONS
	 */
	@RequestMapping("food-calendar")
	public String show_calendar(Model model) {
		
		FoodCalendarDAO foodCalendarDao = (FoodCalendarDAO) ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
		List<FoodCalendar> food_calendar_items = foodCalendarDao.getCalendarItemsWithSumFoodItems();

		model.addAttribute("page_title", "Kirjaukset");
		model.addAttribute("foodcalendar",food_calendar_items);
		
		this.initSidebarData(model);
		return "foodcalendar";	
	}
	
	@RequestMapping("calendar-items/{id}")
	public String show_calendar_item(@PathVariable int id, Model model) {
		//food_items_repository.deleteById(id);
		FoodCalendar calendar_item = food_calendar_repository.getReferenceById(id);
		
		model.addAttribute("foodcalendar",calendar_item);
		
		this.initSidebarData(model);
		return "foodcalendar";	
	}
	
	@RequestMapping("add-calendar-item")
	public String show_add_calendar_item(Model model) {
		FoodCalendar item = new FoodCalendar();
		item.setAdd_date(LocalDateTime.now());
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);	
		List<FoodItems> food_items = foodItemDao.getAll();
		
		model.addAttribute("page_title", "Lisää kirjaus");
		model.addAttribute("food_items",food_items);
		model.addAttribute("calendar_item",item);
		
		this.initSidebarData(model);
		return "add_calendaritem";	
	}
	
	@RequestMapping("save-new-calendar-item")
	public String saveNewCalendarItem(@RequestParam(required = false) String[]food_id,
									  @RequestParam(required = false) String[]name,
									  @RequestParam(required = false) String[]piece,
									  @RequestParam(required = false) String[]gram,
									  @RequestParam(required = false) String[]kcal,
									  @RequestParam(required = false) String[]kj,
									  @RequestParam(required = false) String[]fat,
									  @RequestParam(required = false) String[]carbo,
									  @RequestParam(required = false) String[]protein,
									  @ModelAttribute("calendar-item") FoodCalendar item) {
		
		FoodCalendarDAO fcDao = (FoodCalendarDAO) ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
	    CalendarItemsDAO calendarItemsDao = (CalendarItemsDAO)ApplicationHelper.getDaoObject(CalendarItemsDAO.class);
	    
	    if(null != food_id) {
	    	Set<CalendarItems> calendar_items = new HashSet<>();
		    int item_cnt = food_id.length;
		    
		    for(int i=0; i < item_cnt; i++) {
		    	CalendarItems calendar_item = new CalendarItems();
		    	if(piece.length == 0)piece = new String[1];
		    	if(gram.length == 0)gram = new String[1];
		    	if(kcal.length == 0)kcal = new String[1];
		    	if(kj.length == 0)kj = new String[1];
		    	if(fat.length == 0)fat = new String[1];
		    	if(carbo.length == 0)carbo = new String[1];
		    	if(protein.length == 0)protein = new String[1];
		    	
		    	calendar_item.setFood_calendar(item);
		    	calendar_item.setName(name[i]);
		    	calendar_item.setPiece(ApplicationHelper.fixValue(piece[i]));
		    	calendar_item.setGram(ApplicationHelper.fixValue(gram[i]));
		    	calendar_item.setKcal(ApplicationHelper.fixValue(kcal[i]));
		    	calendar_item.setKj(ApplicationHelper.fixValue(kj[i]));
		    	calendar_item.setFat(ApplicationHelper.fixValue(fat[i]));
		    	calendar_item.setCarbohydrate(ApplicationHelper.fixValue(carbo[i]));
		    	calendar_item.setProtein(ApplicationHelper.fixValue(protein[i]));
		    	calendar_items.add(calendar_item);
		    }
		    item.setCalendar_items(calendar_items);
	    }
	    
		fcDao.saveOrUpdate(item);
	
	    return "redirect:/food-calendar";	
	}

	@RequestMapping("edit-calendar-item/{id}")
	public String edit_calendar_item(@PathVariable int id, Model model) {
				
		FoodCalendarDAO foodCalendarDao = (FoodCalendarDAO) ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
		FoodCalendar calendar_item = foodCalendarDao.getById(id);
		
		FoodItemsDAO foodItemDao = (FoodItemsDAO) ApplicationHelper.getDaoObject(FoodItemsDAO.class);	
		List<FoodItems> food_items = foodItemDao.getAll();
		
		model.addAttribute("page_title", "Muokka kirjausta");
		model.addAttribute("food_items",food_items);
		model.addAttribute("calendar_item",calendar_item);
		
		this.initSidebarData(model);
		return "edit_calendaritem";	
	}
	
	@RequestMapping("save-old-calendar-item/{id}")
	public String saveOldCalendarItem(@RequestParam(required = false) String[]food_id,
									  @RequestParam(required = false) String[]name,
									  @RequestParam(required = false) String[]piece,
									  @RequestParam(required = false) String[]gram,
									  @RequestParam(required = false) String[]kcal,
									  @RequestParam(required = false) String[]kj,
									  @RequestParam(required = false) String[]fat,
									  @RequestParam(required = false) String[]carbo,
									  @RequestParam(required = false) String[]protein,
									  @ModelAttribute("calendar-item") FoodCalendar item) {
								
		FoodCalendarDAO fcDao = (FoodCalendarDAO) ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
		CalendarItemsDAO itemsDao = (CalendarItemsDAO) ApplicationHelper.getDaoObject(CalendarItemsDAO.class);
		
		//delete food items
		List<CalendarItems> cal_items = itemsDao.getAllById(item.getId());
		for(CalendarItems i : cal_items) {
			itemsDao.deleteById(i);
		}
		
		Set<CalendarItems> calendar_items = new HashSet<>();
	    if(null != food_id) {
	    	
		    int item_cnt = food_id.length;
		    
		    for(int i=0; i < item_cnt; i++) {
		    	CalendarItems calendar_item = null;
		    	calendar_item = itemsDao.getById(Integer.valueOf(food_id[i]));
		    	if(calendar_item == null) {
		    		calendar_item = new CalendarItems();
		    	}
		    	
		    	if(piece.length == 0)piece = new String[1];
		    	if(gram.length == 0)gram = new String[1];
		    	if(kcal.length == 0)kcal = new String[1];
		    	if(kj.length == 0)kj = new String[1];
		    	if(fat.length == 0)fat = new String[1];
		    	if(carbo.length == 0)carbo = new String[1];
		    	if(protein.length == 0)protein = new String[1];
		    	
		    	calendar_item.setFood_calendar(item);
		    	calendar_item.setName(name[i]);
		    	calendar_item.setPiece(ApplicationHelper.fixValue(piece[i]));
		    	calendar_item.setGram(ApplicationHelper.fixValue(gram[i]));
		    	calendar_item.setKcal(ApplicationHelper.fixValue(kcal[i]));
		    	calendar_item.setKj(ApplicationHelper.fixValue(kj[i]));
		    	calendar_item.setFat(ApplicationHelper.fixValue(fat[i]));
		    	calendar_item.setCarbohydrate(ApplicationHelper.fixValue(carbo[i]));
		    	calendar_item.setProtein(ApplicationHelper.fixValue(protein[i]));
		    	calendar_items.add(calendar_item);
		    }
		    item.setCalendar_items(calendar_items);
	    }
	    fcDao.saveOrUpdate(item);
	    
	    return "redirect:/food-calendar";	
	}
	
	@RequestMapping("del-calendar-item/{id}")
	public String delete_calendar_item(@PathVariable int id, Model model) {
		food_calendar_repository.deleteById(id);
		return "redirect:/food-calendar";		
	}
	// END CALENDAR FUNCTIONS
	
	private void initSidebarData(Model model) {
		FoodCalendarDAO foodCalendarDao = (FoodCalendarDAO) ApplicationHelper.getDaoObject(FoodCalendarDAO.class);
		
		FoodCalendar last_item = foodCalendarDao.getLastAdded();
		LocalDateTime addDate = (null == last_item) ? LocalDateTime.now() : last_item.getAdd_date(); 
		model.addAttribute("last_added", addDate);
		
        Calendar cal = new GregorianCalendar(Locale.getDefault());
        Date today = new Date();
        cal.setTime(today);
        int current_week = cal.get(Calendar.WEEK_OF_YEAR);
        model.addAttribute("current_week",current_week);
        
	    LocalDate currDate = LocalDate.now();
	    List<LocalDate> weekData = ApplicationHelper.getDaysOfCurrentWeek();
	    Iterator<LocalDate> iterator = weekData.iterator();	   
	    List<String> weekdata_with_calories = foodCalendarDao.getCalendarItemsWithCaloriesSum(FoodCalendarDAO.TIME_PERIOD.WEEK);

	    model.addAttribute("current_week_days",weekdata_with_calories);
	    model.addAttribute("MAX_CALORIES_PER_DAY", FoodCalendar.MAX_CALORIES_PER_DAY);

  	}	
	

}


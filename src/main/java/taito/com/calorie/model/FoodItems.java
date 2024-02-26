package taito.com.calorie.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItems extends AbstractModel{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String name;
	private Double kj;
	private Double kcal;
	private Double fat = 0.0;
	private Double carbohydrate = 0.0;
	private Double protein = 0.0;

//	@ManyToMany(mappedBy = "food_items", cascade = { CascadeType.ALL })
//	@ManyToOne
//	@JoinColumn(name = "foodcalendar_id", nullable = false)
//	private Set<FoodCalendar> food_calendar = new HashSet<FoodCalendar>();
	
	
	public FoodItems() {
	}

	public FoodItems(Integer id, String name, Double kj, Double kcal, Double fat, Double carbohydrate, Double protein,
			Set<FoodCalendar> food_calendar) {
		super();
		this.id = id;
		this.name = name;
		this.kj = kj;
		this.kcal = kcal;
		this.fat = fat;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
//		this.food_calendar = food_calendar;
	}

	@Override
	public String toString() {
		return "FoodItems [id=" + id + ", name=" + name + ", kj=" + kj + ", kcal=" + kcal + ", fat=" + fat
				+ ", carbohydrate=" + carbohydrate + ", protein=" + protein + "]";
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getKj() {
		return kj;
	}

	public void setKj(Double kj) {
		this.kj = kj;
	}

	public Double getKcal() {
		return kcal;
	}

	public void setKcal(Double kcal) {
		this.kcal = kcal;
	}

//	public Set<FoodCalendar> getFood_calendar() {
//		return food_calendar;
//	}
//
//	public void setFood_calendar(Set<FoodCalendar> food_calendar) {
//		this.food_calendar = food_calendar;
//	}

	public Double getFat() {
		return fat;
	}

	public void setFat(Double fat) {
		this.fat = fat;
	}

	public Double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(Double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public Double getProtein() {
		return protein;
	}

	public void setProtein(Double protein) {
		this.protein = protein;
	}

}

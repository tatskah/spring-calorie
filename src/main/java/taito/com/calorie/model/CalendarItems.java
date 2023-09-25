package taito.com.calorie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendar_items")
public class CalendarItems extends AbstractModel{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	private String name;
	private Double piece;
	private Double gram;
	private Double kj;
	private Double kcal;
	private Double fat = 0.0;
	private Double carbohydrate = 0.0;
	private Double protein = 0.0;
	
//	@JoinColumn(name = "foodcalendar_id", nullable = false)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "foodcalendar_id", referencedColumnName="id")
	private FoodCalendar food_calendar;	
	
	public CalendarItems() {}

	public CalendarItems(Integer id, String name, Double piece, Double gram, Double kj, Double kcal, Double fat,
			Double carbohydrate, Double protein, FoodCalendar food_calendar) {
		super();
		this.id = id;
		this.name = name;
		this.piece = piece;
		this.gram = gram;
		this.kj = kj;
		this.kcal = kcal;
		this.fat = fat;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.food_calendar = food_calendar;
	}

	@Override
	public String toString() {
		return "CalendarItems [id=" + id + ", name=" + name + ", piece=" + piece + ", gram=" + gram + ", kj=" + kj
				+ ", kcal=" + kcal + ", fat=" + fat + ", carbohydrate=" + carbohydrate + ", protein=" + protein
				+ ", food_calendar=" + food_calendar + "]";
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

	public Double getPiece() {
		return piece;
	}

	public void setPiece(Double piece) {
		this.piece = piece;
	}

	public Double getGram() {
		return gram;
	}

	public void setGram(Double gram) {
		this.gram = gram;
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

	public FoodCalendar getFood_calendar() {
		return food_calendar;
	}

	public void setFood_calendar(FoodCalendar food_calendar) {
		this.food_calendar = food_calendar;
	}


}

package taito.com.calorie.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Map;
import java.util.HashMap;

@Entity
@Table(name = "food_calendar")
public class FoodCalendar extends AbstractModel{

	private static final long serialVersionUID = 1L;
	
    @Id
    @Column//(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	@Column(name = "add_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime add_date;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="food_calendar", cascade = CascadeType.ALL)
	private Set<CalendarItems> calendar_items;
	
	@Transient
	public double kcal_total;
	@Transient
	public double kj_total;
	@Transient
	public double fat_total;
	@Transient
	public double protein_total;
	@Transient
	public double piece_total;
	@Transient
	public double gram_total;

	@Transient
	public static final int MAX_CALORIES_PER_DAY = 1200;
	
	@Transient
	Map<String, Integer> sideBarWeekData = new HashMap<String, Integer>();
	
	public FoodCalendar() {}


	public FoodCalendar(Integer id, LocalDateTime add_date, Set<CalendarItems> calendar_items, double kcal_total,
			double kj_total, double fat_total, double protein_total) {
		super();
		this.id = id;
		this.add_date = add_date;
		this.calendar_items = calendar_items;
		this.kcal_total = kcal_total;
		this.kj_total = kj_total;
		this.fat_total = fat_total;
		this.protein_total = protein_total;
	}



	@Override
	public String toString() {
		return "FoodCalendar [id=" + id + ", add_date=" + add_date + ", calendar_items=" + calendar_items
				+ ", kcal_total=" + kcal_total + ", kj_total=" + kj_total + ", fat_total=" + fat_total
				+ ", protein_total=" + protein_total + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getAdd_date() {
		return add_date;
	}

	public void setAdd_date(LocalDateTime add_date) {
		this.add_date = add_date;
	}

	public double getKcal_total() {
		return kcal_total;
	}

	public void setKcal_total(double kcal_total) {
		this.kcal_total = kcal_total;
	}

	public double getKj_total() {
		return kj_total;
	}

	public void setKj_total(double kj_total) {
		this.kj_total = kj_total;
	}

	public Set<CalendarItems> getCalendar_items() {
		return calendar_items;
	}

	public void setCalendar_items(Set<CalendarItems> calendar_items) {
		this.calendar_items = calendar_items;
	}

	public double getFat_total() {
		return fat_total;
	}

	public void setFat_total(double fat_total) {
		this.fat_total = fat_total;
	}

	public double getProtein_total() {
		return protein_total;
	}

	public void setProtein_total(double protein_total) {
		this.protein_total = protein_total;
	}
	
	public double getPiece_total() {
		return piece_total;
	}

	public void setPiece_total(double piece_total) {
		this.piece_total = piece_total;
	}

	public double getGram_total() {
		return gram_total;
	}

	public void setGram_total(double gram_total) {
		this.gram_total = gram_total;
	}

	public Map<String, Integer> getSideBarWeekData() {
		return sideBarWeekData;
	}

	public void setSideBarWeekData(Map<String, Integer> sideBarWeekData) {
		this.sideBarWeekData = sideBarWeekData;
	}
	
	
	
}

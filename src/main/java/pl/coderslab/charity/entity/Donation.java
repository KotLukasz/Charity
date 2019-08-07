package pl.coderslab.charity.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Donation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int quantity;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Category> categories;

	@OneToOne
	@JoinColumn(name = "institution_id")
	private Institution institution;

	@ManyToOne
	private User user;

	private String street;

	private String city;

	private String zipCode;

	private String telephone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pickUpDate;

	private LocalTime pickUpTime;

	private String pickUpComment;

	private boolean pickedUp;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pickedUpDate;

	public Donation() {
	}

	public Long getId() {
		return id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public LocalTime getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(LocalTime pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

	public String getPickUpComment() {
		return pickUpComment;
	}

	public void setPickUpComment(String pickUpComment) {
		this.pickUpComment = pickUpComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	public LocalDate getPickedUpDate() {
		return pickedUpDate;
	}

	public void setPickedUpDate(LocalDate pickedUpDate) {
		this.pickedUpDate = pickedUpDate;
	}
}

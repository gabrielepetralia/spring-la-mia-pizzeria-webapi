package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = true)
	@Length(
			min = 3, 
			max = 30, 
			message = "Il nome deve essere composto da 3 a 128 caratteri"
		)
	private String name;
	
	@Length(
			min = 3, 
			max = 255, 
			message = "La descrizione deve essere lunga da 3 a 255 caratteri"
		)
	private String description;
	
	@NotBlank(message = "Inserire l\' url dell\' immagine")
	private String imgUrl;
	
	@NotNull(message = "Inserire il prezzo")
	@DecimalMin(value = "0.00", inclusive = false, message="Il prezzo non può essere inferiore o uguale a 0")
	private double price;
	
	@OneToMany(mappedBy = "pizza")
	@JsonManagedReference
	private List<SpecialOffer> specialOffers;
	
	@ManyToMany
	@JsonManagedReference
	private List<Ingredient> ingredients;
	
	public Pizza() { }
	
	public Pizza(String name, String description, String imgUrl, double price, Ingredient...ingredients) {
		setName(name);
		setDescription(description);
		setImgUrl(imgUrl);
		setPrice(price);
		setIngredients(Arrays.asList(ingredients));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String img) {
		this.imgUrl = img;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getFormattedPrice() {
		return String.format("%.2f", price);
	}

	public List<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}

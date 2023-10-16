package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	private Boolean allergens;
	
	@ManyToMany(mappedBy = "ingredients")
	@JsonBackReference
	private List<Pizza> pizzas;
	
	public Ingredient() {};
	
	public Ingredient(String name, Boolean allergens, Pizza...pizzas) {
		setName(name);
		setAllergens(allergens);
		setPizzas(Arrays.asList(pizzas));
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
	
	public Boolean getAllergens() {
		return allergens;
	}
	
	public void setAllergens(Boolean allergens) {
		this.allergens = allergens;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
}

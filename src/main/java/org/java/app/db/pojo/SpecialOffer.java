package org.java.app.db.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	@Column(nullable = false)
	private LocalDate endDate;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Pizza pizza;
	
	public SpecialOffer() {}
	
	public SpecialOffer(LocalDate startDate, LocalDate endDate, String title, Pizza pizza) {
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
		setPizza(pizza);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public String getHtmlStartDate() {

		return getStartDate() == null
				? null
				: getStartDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public void setHtmlStartDate(String date) {
		setStartDate(LocalDate.parse(date));
	}
	
	public String getHtmlEndDate() {

		return getEndDate() == null
				? null
				: getEndDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public void setHtmlEndDate(String date) {
		setEndDate(LocalDate.parse(date));
	}
	
}

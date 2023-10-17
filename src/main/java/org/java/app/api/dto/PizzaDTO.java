package org.java.app.api.dto;

public class PizzaDTO {

	private String name;
	private String description;
	private String imgUrl;
	private double price;
	
	public PizzaDTO() { }
	public PizzaDTO(String name, String description, String imgUrl, double price) {
		
		setName(name);
		setDescription(description);
		setImgUrl(imgUrl);
		setPrice(price);
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

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public double getPrice() {
		return price;
		
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

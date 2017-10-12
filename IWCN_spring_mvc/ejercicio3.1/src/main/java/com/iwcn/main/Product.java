package com.iwcn.main;

public class Product {

	int numCode;
	String name;
	String description;
	double price;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}	

	public Product(int numCode, String name, String description, double price) {
		super();
		this.numCode = numCode;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getNumCode() {
		return numCode;
	}

	public void setNumCode(int numCode) {
		this.numCode = numCode;
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

	public void setDescripction(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}

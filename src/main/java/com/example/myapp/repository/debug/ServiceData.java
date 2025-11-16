package com.example.myapp.repository.debug;

public class ServiceData extends TKHRData{
	private String name;
	private int price;
	private String text;
	
	public ServiceData(int id, String name,int price,String text) {
		super(id);
		this.name = name;
		this.price = price;
		this.text = text;
	}


	public String getName() {
		return name;
	}


	public int getPrice() {
		return price;
	}


	public String getText() {
		return text;
	}

}

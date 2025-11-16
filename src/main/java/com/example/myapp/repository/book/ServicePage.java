package com.example.myapp.repository.book;

public class ServicePage extends BookPage {
	private int price;

	public ServicePage(int id, String name,int price) {
		super(id, name);
		defaultPageName("service");
		this.price = price;
	}
	
	public ServicePage(ServicePage page) {
		super(page);
		setPageName(page.getPageName());
		price = page.getPrice();
	}
	public int getPrice() {
		return price;
	}

}

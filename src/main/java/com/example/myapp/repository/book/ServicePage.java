package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

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
	
	public void setParam(int columnId, String after) {
		switch (columnId) {
		case 1:
			name = after;
			break;
		case 2:
			price = Integer.parseInt(after);
			break;
		case 3:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
	}
	
	public int getPrice() {
		return price;
	}

}

package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

public class ItemPage extends BookPage {
	private String furigana;
	private int price;

	public ItemPage(int id, String name, String furigana, int price) {
		super(id, name);
		defaultPageName("item");
		this.furigana = furigana;
		this.price = price;
	}

	public ItemPage(ItemPage page) {
		super(page);
		setPageName(page.getPageName());
		furigana = page.getFurigana();
		price = page.getPrice();
	}

	public void setParam(int columnId, String after) {
		switch (columnId) {
		case 1:
			name = after;
			break;
		case 2:
			furigana = after;
			break;
		case 3:
			price = Integer.parseInt(after);
			break;
		case 4:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
	}

	public String getFurigana() {
		return furigana;
	}

	public int getPrice() {
		return price;
	}

}

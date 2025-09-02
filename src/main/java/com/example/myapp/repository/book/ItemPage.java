package com.example.myapp.repository.book;

public class ItemPage extends BookPage {
	private int price;
	private String furigana;

	public ItemPage(int id, String name, String furigana, int price) {
		super(id, name);
		this.furigana = furigana;
		this.price = price;
	}
	
	public String getFurigana() {
		return furigana;
	}
	public int getPrice() {
		return price;
	}

}

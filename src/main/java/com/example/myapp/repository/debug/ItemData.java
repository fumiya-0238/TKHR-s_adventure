package com.example.myapp.repository.debug;

public class ItemData extends TKHRData {
	private String name;
	private int price;
	private String furigana;
	private String text;

	public ItemData(int id, String name, String furigana, int price, String text) {
		super(id);
		this.name = name;
		this.furigana = furigana;
		this.price = price;
		this.text = text;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getFurigana() {
		return furigana;
	}

	public String getText() {
		return text;
	}

}

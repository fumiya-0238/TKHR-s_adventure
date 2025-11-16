package com.example.myapp.repository.book;

public class WeaponPage extends BookPage {
	private int price;
	private int attack;
	private String furigana;

	public WeaponPage(int id, String name, String furigana, int attack, int price) {
		super(id, name);
		this.price = price;
		this.furigana = furigana;
		this.attack = attack;
		defaultPageName("weapon");
	}

	public WeaponPage(WeaponPage page) {
		super(page);
		setPageName(page.getPageName());
		price = page.getPrice();
		furigana = page.getFurigana();
		attack = page.getAttack();
	}

	public String getFurigana() {
		return furigana;
	}

	public int getAttack() {
		return attack;
	}

	public int getPrice() {
		return price;
	}
}

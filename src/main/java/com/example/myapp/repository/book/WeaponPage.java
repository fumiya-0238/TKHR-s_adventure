package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

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
			attack = Integer.parseInt(after);
			break;
		case 5:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
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

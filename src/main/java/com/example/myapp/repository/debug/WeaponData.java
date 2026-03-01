package com.example.myapp.repository.debug;

public class WeaponData extends TKHRData {
	private String name;
	private String furigana;
	private int price;
	private int attack;
	private String text;

	public WeaponData(int id, String name, String furigana, int price,int attack, String text) {
		super(id);
		this.name = name;
		this.furigana = furigana;
		this.price = price;
		this.attack = attack;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public String getFurigana() {
		return furigana;
	}

	public int getPrice() {
		return price;
	}

	public int getAttack() {
		return attack;
	}

	public String getText() {
		return text;
	}

}

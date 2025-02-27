package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public abstract class Item {
	protected String name;
	private int price;
	protected String text;
	protected boolean unique;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	protected abstract String useResult(Battle battle, int i);

	public String commonUse(Battle battle, int i) {
		StringBuilder sb = new StringBuilder(name);
		sb.append("を使った。");
		sb.append(useResult(battle, i));
		return sb.toString();
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

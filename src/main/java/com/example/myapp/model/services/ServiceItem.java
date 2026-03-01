package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class ServiceItem {
	private int id;
	private String name;
	protected int price;

	public void setStatus(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public abstract void buy(Battle battle, int price);

	public void commonBuy(Battle battle, int price) {
		battle.getPlayer().plusGold(-price);
		Battle.addLogs(new ScreenChange(ScreenEnum.買う, String.valueOf(price)));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}

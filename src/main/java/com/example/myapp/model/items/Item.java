
package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Item {
	protected int id;
	protected String name;
	protected int price;
	protected boolean active;

	public void setStatus(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public abstract void use(Battle battle, List<ActionInfo> infos);

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void turnStart(Battle battle, List<ActionInfo> infos) {
	}
	
	public void turnEnd(Battle battle, List<ActionInfo> infos) {
	}
	
	public void addItem(Player player) {
	}
	
	public void removeItem(Player player) {
	}
	
	public boolean getActive() {
		return active;
	}
}

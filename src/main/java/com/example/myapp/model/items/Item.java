
package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Item {
	protected int id;
	protected String name;
	protected int price;
	protected boolean active;
	private List<String> relateds;

	public void setStatus(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public abstract void use(Battle battle, List<ActionInfo> infos, int n);
	protected void commonUse(ActionInfo info) {
		info.addMessages("プレイヤーは"+name+"を使った");
	}
	
	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}


	public boolean getActive() {
		return active;
	}
}

package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public abstract class SubscriptionItem extends Item {
	private boolean active;

	public SubscriptionItem(String name, int price) {
		super(name, price);
	}

	protected void registration(Battle battle, int i, CreateCondition condition) {
		if (active) {
			battle.getPlayer().plusCondition(condition);
		} else {
			battle.getPlayer().plusCondition(condition);
			active = true;
		}
	}
}

package com.example.myapp.model.items;

import com.example.myapp.service.GameService;

public abstract class SubscriptionItem extends Item {
	private boolean active;

	public SubscriptionItem(String name, int price) {
		super(name, price);
	}

	protected void registration(GameService battle, int i) {
		if (active) {
			battle.removeSubscriptionItem(subscriptionItem);
		} else {
			battle.addSubscriptionItem(subscriptionItem);
			active = true;
		}
	}
}

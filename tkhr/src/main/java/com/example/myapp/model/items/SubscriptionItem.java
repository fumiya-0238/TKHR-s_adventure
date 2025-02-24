package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public abstract class SubscriptionItem extends Item {
	private boolean active;

	public SubscriptionItem(String name, int price) {
		super(name, price);
	}

	protected void registration(Battle battle, int i, ItemInterface itemInterface) {
		if (active) {
			battle.removeSubscriptionItem(subscriptionItem);
			itemInterface.removeItem(i);
		} else {
			battle.addSubscriptionItem(subscriptionItem);
			itemInterface.activeSub(i);
			active = true;
		}
	}
}

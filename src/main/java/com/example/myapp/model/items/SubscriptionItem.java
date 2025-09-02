package com.example.myapp.model.items;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.Battle;

public abstract class SubscriptionItem extends Item {
	public void registration(Battle battle, ConditionEnum conditionEnum) {
		battle.getPlayer().plusCondition(battle, conditionEnum);
		active = true;
	}

	public void cancel(Battle battle, ConditionEnum conditionEnum) {
		battle.getPlayer().removeCondition(conditionEnum);
		active = false;
	}
	 public abstract void registration(Battle battle);
	 public abstract void cancel(Battle battle);
}
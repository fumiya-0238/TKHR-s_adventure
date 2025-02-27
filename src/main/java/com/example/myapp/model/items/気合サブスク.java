package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class 気合サブスク extends SubscriptionItem {
	public 気合サブスク() {
		super("気合サブスク", 40);
		setText("毎ターン40G消費し、気合を25%上げます。");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		super.registration(battle, i, CreateCondition.TENTION_SUB);
	}
}

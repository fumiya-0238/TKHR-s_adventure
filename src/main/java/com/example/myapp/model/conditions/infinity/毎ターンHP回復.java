package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.Battle;

public class 毎ターンHP回復 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	@Override
	public void turnEnd(Battle battle,Living living) {
		living.setHeal(getAmount());
	}
	public void setName() {
		setName("毎ターンHP"+getAmount()+"回復");
	}
}

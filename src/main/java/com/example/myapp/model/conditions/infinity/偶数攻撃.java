package com.example.myapp.model.conditions.infinity;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.Battle;

public class 偶数攻撃 extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

	@Override
	public void monsterAction(Battle battle) {
		if (battle.getMonster().getHP() % 2 != 0) {
			battle.getMonster().setAction(CreateAction.INSTANCE.create(35));
		}
	}
}

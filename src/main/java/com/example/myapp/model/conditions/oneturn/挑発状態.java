package com.example.myapp.model.conditions.oneturn;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.Battle;

public class 挑発状態 extends Condition {
	@Override
	public void monsterAction(Battle battle) {
		battle.conditionMessage(battle.getMonster(), name);
		battle.getMonster().setAction(CreateAction.INSTANCE.create(2));
	}
}

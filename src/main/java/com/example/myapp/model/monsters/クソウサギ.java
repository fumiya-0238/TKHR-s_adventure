package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class クソウサギ extends Monster {
	public クソウサギ(int ID) {
		name = "クソウサギ";
		HP = 7;
		OverHP = 11;
		ATK = 3;
		EXP = 5;
		Gold = 14;
		Turn = 4;
		this.ID = ID;
		super.init();
		plusCondition(CreateCondition.TENACITY);

	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		AttackActionList.INSTANCE.normalAttack(battle, info);
	}
}

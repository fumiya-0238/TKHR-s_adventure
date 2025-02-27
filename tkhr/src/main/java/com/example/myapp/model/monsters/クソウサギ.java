package com.example.myapp.model.monsters;

import com.example.myapp.service.GameService;
import com.example.myapp.model.monsters.actions.AttackActionList;
import com.example.myapp.model.conditions.CreateCondition;

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
		plusMonsterCondition(CreateCondition.TENACITY);

	}

	@Override
	public void actions(GameService battle) {
		AttackActionList.INSTANCE.normalAttack(battle);
	}
}

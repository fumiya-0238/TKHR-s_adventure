package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.monsters.actions.AttackActionList;
import com.example.myapp.model.conditions.CreateCondition;

public class エンシェントカニ extends Monster {
	public エンシェントカニ(int ID) {
		name = "エンシェントカニ";
		HP = 90;
		OverHP = 115;
		ATK = 12;
		EXP = 13;
		Gold = 88;
		Turn = 9;
		this.ID = ID;
		plusCondition(CreateCondition.WEEK_INVALID);
		super.init();
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		AttackActionList.INSTANCE.normalAttack(battle, info);
	}

}

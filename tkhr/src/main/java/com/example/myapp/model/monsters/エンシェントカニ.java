package com.example.myapp.model.monsters;

import com.example.myapp.service.GameService;
import com.example.myapp.model.monsters.actions.AttackActionList;


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
		plusMonsterCondition(CreateCondition.WEEK_INVALID);
		super.init();
	}

	@Override
	public void actions(GameService battle) {
		AttackActionList.INSTANCE.normalAttack(battle);
	}

}

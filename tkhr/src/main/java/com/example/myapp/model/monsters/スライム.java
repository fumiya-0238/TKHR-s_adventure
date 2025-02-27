package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class スライム extends Monster {
	public スライム(int ID) {
		name = "スライム";
		HP = 3;
		OverHP = 5;
		ATK = 2;
		EXP = 2;
		Gold = 8;
		Turn = 3;
		this.ID = ID;
		super.init();
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		AttackActionList.INSTANCE.normalAttack(battle, info);
	}

}

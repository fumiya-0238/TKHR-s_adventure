package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class ネペント extends Monster {
	public ネペント(int ID) {
		name = "ネペント";
		HP = 21;
		OverHP = 29;
		ATK = 8;
		EXP = 7;
		Gold = 16;
		Turn = 4;
		this.ID = ID;
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		if (HP % 3 == 0)
			AttackActionList.INSTANCE.normalAttack(battle, info);
		else
			AttackActionList.INSTANCE.drainAttack(battle, info);
	}
}

package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.monsters.actions.AssistActionList;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class 草太郎 extends Monster {
	public 草太郎(int ID) {
		name = "草太郎";
		HP = 6;
		OverHP = 10;
		ATK = 3;
		EXP = 4;
		Gold = 10;
		Turn = 4;
		this.ID = ID;
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		if (HP % 3 == 0)
			AssistActionList.INSTANCE.heal(battle, 3);
		else
			AttackActionList.INSTANCE.normalAttack(battle, info);
	}
}

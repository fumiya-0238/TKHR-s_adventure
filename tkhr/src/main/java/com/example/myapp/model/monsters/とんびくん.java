package com.example.myapp.model.monsters;

import com.example.myapp.service.GameService;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class とんびくん extends Monster {
	public とんびくん(int ID) {
		name = "とんびくん";
		HP = 35;
		OverHP = 52;
		ATK = 10;
		EXP = 100;
		Gold = 5;
		Turn = 5;
		this.ID = ID;
	}

	@Override
	public void actions(GameService battle) {
		if (HP % 3 == 0)
			AttackActionList.INSTANCE.pecking(battle);
		else
			AttackActionList.INSTANCE.panetrateAttack(battle);
	}
}

package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.monsters.actions.AssistActionList;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class 暴走培養 extends Monster {
	public 暴走培養(int ID) {
		name = "暴走培養";
		HP = 102;// 3
		OverHP = 120;
		ATK = 14;
		EXP = 16;
		Gold = 26;
		Turn = 10;
		this.ID = ID;
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		if (Turn == 10 || Turn == 8 || Turn == 5 || Turn == 3 || Turn == 2 || Turn == 1)
			AttackActionList.INSTANCE.runawayLaserAttack(battle, info);
		else
			AttackActionList.INSTANCE.normalAttack(battle, info);
	}
}
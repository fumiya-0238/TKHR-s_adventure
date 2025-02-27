package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.monsters.actions.AttackActionList;

public class 生首培養 extends Monster {
	public 生首培養(int ID) {
		name = "生首培養";
		HP = 17;
		OverHP = 24;
		ATK = 6;
		EXP = 6;
		Gold = 15;
		Turn = 4;
		this.ID = ID;
		super.init();
		plusCondition(CreateCondition.CONTROL_SWITCH);
	}

	@Override
	public void actions(Battle battle, ActionInfo info) {
		// TODO 自動生成されたメソッド・スタブ
		if (Turn == 3 || Turn == 1)
			AttackActionList.INSTANCE.normalAttack(battle, info);
		else
			AttackActionList.INSTANCE.laserAttack(battle, info);
	}
}

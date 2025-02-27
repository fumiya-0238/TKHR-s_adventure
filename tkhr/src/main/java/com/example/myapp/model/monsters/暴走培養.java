package com.example.myapp.model.monsters;

import com.example.myapp.service.GameService;
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
	public void actions(GameService battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (Turn == 10 || Turn == 8 || Turn == 5 || Turn == 3 || Turn == 2 || Turn == 1)
			AttackActionList.INSTANCE.laserRunawayAttack(battle);
		else
			AttackActionList.INSTANCE.normalAttack(battle);
	}
}
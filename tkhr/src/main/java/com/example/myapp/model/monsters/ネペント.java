package com.example.myapp.model.monsters;

import tkhr.Battle;
import tkhr.monsters.actions.AttackActionList;


public class ネペント extends Monster{
	public ネペント(int ID) {
		name="ネペント";
		HP=21;
		OverHP=29;
		ATK=8;
		EXP=7;
		Gold=16;
		Turn=4;
		this.ID=ID;
	}
	
	@Override
	public void actions(GameService battle) {
		if(HP%3==0)
		AttackActionList.INSTANCE.normalAttack(battle);
		else
		AttackActionList.INSTANCE.drainAttack(battle);
	}
}

package com.example.myapp.model.weapons;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 入魂剣 extends Weapon {
	public 入魂剣() {
		super("入魂剣", 55, "強攻撃をした後、1回だけHPが0になる攻撃を受けてもHP1で耐える", 5);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		battle.getPlayer().plusCondition(CreateCondition.GUTS);
	}
}

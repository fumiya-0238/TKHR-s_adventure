package com.example.myapp.model.weapons;

import tkhr.Battle;

public class 入魂剣 extends Weapon {
	public 入魂剣() {
		super("入魂剣", 55, "強攻撃をした後、1回だけHPが0になる攻撃を受けてもHP1で耐える", 5);
	}

	@Override
	public void criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		battle.getPlayer().plusPlayerCondition("根性状態");
	}
}

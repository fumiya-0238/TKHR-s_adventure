package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 強強剣 extends Weapon {
	public 強強剣() {
		super("強強剣", 65, "強攻撃の倍率が1.5倍から2倍になる", 3);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		info.setDamage(battle.getPlayer().getATK() * 2);
	}
}

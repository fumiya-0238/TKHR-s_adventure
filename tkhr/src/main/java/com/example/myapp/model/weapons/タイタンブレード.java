package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class タイタンブレード extends Weapon {
	public タイタンブレード() {
		super("タイタンブレード", 100, "強攻撃をしたターン、同時に防御もする。", 10);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		super.criticalAttack(battle, info);
	}
}

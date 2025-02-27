package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 貫通剣 extends Weapon {
	public 貫通剣() {
		super("貫通剣", 70, "全ての攻撃が貫通攻撃になる", 6);
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		info.setPenetrate(true);
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		info.setPenetrate(true);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		info.setPenetrate(true);
	}
}

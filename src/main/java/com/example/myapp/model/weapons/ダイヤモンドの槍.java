package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ダイヤモンドの槍 extends Weapon {
	public ダイヤモンドの槍() {
		super("ダイヤモンドの槍", 70, "強攻撃回数+2。全ての攻撃が貫通攻撃になる。", 8);
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

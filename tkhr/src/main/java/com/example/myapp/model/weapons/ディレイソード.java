package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ディレイソード extends Weapon {
	private int delay;

	public ディレイソード() {
		super("ディレイソード", 70, "攻撃しても相手のHPが減らずにダメージが蓄積される。防御をすると、それまで蓄積されていた分のダメージを与える。", 12);
	}

	private void setDelay(Battle battle, ActionInfo info) {
		delay += battle.getMonster().simulateDamage(battle, info);
		info.setDamage(0);
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		setDelay(battle, info);
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		setDelay(battle, info);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		setDelay(battle, info);
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		info.setAttackIsTrue();
		info.setDamage(delay);
		info.setPenetrate(true);
		delay = 0;
	}
}

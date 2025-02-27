package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.CreateCondition;

public class 強強剣 extends Weapon {
	public 強強剣() {
		super("強強剣", 65, "強攻撃の倍率が1.5倍から2倍になる", 3);
	}

	@Override
	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() * 2;
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, (player.amountCondition(CreateCondition.PENETRATE) > 0), battle);
	}
}

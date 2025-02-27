package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.CreateCondition;

public class タイタンブレード extends Weapon {
	public タイタンブレード() {
		super("タイタンブレード", 100, "強攻撃をしたターン、同時に防御もする。", 10);
	}

	@Override
	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, (player.amountCondition(CreateCondition.PENETRATE) > 0), battle);
		super.defence(battle);
	}
}

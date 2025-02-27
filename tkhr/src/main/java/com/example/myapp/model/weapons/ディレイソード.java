package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.CreateCondition;

public class ディレイソード extends Weapon {
	private int delay;

	public ディレイソード() {
		super("ディレイソード", 70, "攻撃しても相手のHPが減らずにダメージが蓄積される。防御をすると、それまで蓄積されていた分のダメージを与える。", 12);
	}

	public void attack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK();
		delay += battle.getMonster().simulateDamage(damage, (player.amountCondition(CreateCondition.PENETRATE) > 0),
				battle);
	}

	public void weekAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		delay += battle.getMonster().simulateDamage(damage, (player.amountCondition(CreateCondition.PENETRATE) > 0),
				battle);
	}

	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		delay += battle.getMonster().simulateDamage(damage, (player.amountCondition(CreateCondition.PENETRATE) > 0),
				battle);
	}

	public void defence(GameService battle) {
		super.defence(battle);
		battle.getMonster().calcDamageResult(delay, true, battle);
		delay = 0;
	}
}

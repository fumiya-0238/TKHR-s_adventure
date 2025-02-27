package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.Player;

public class 貫通剣 extends Weapon {
	public 貫通剣() {
		super("貫通剣", 70, "全ての攻撃が貫通攻撃になる", 6);
	}

	public void attack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK();
		battle.getMonster().calcDamageResult(damage, true, battle);
	}

	public void weekAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		battle.getMonster().calcDamageResult(damage, true, battle);
	}

	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, true, battle);
	}
}

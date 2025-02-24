package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;

public class 天使の剣 extends Weapon {
	public 天使の剣() {
		super("天使の剣", 60, "ターン終了時、自分のHPが10%回復", 3);
	}

	public void attack(Battle battle) {
		super.attack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void weekAttack(Battle battle) {
		super.weekAttack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void criticalAttack(Battle battle) {
		super.criticalAttack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void defence(Battle battle) {
		super.defence(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void tension(Battle battle) {
		super.tension(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}
}

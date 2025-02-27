package com.example.myapp.model.weapons;

import tkhr.Battle;
import tkhr.Player;

public class 天使の剣 extends Weapon {
	public 天使の剣() {
		super("天使の剣", 60, "ターン終了時、自分のHPが10%回復", 3);
	}

	public void attack(GameService battle) {
		super.attack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void weekAttack(GameService battle) {
		super.weekAttack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void defence(GameService battle) {
		super.defence(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	public void tension(GameService battle) {
		super.tension(battle);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}
}

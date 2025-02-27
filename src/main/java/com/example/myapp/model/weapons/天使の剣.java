package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 天使の剣 extends Weapon {
	public 天使の剣() {
		super("天使の剣", 60, "ターン終了時、自分のHPが10%回復", 3);
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}

	@Override
	public void tension(Battle battle, ActionInfo info) {
		super.tension(battle, info);
		Player player = battle.getPlayer();
		player.healResult(player.getMAXHP() / 10);
	}
}

package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.items.火の玉;

public class 火炎の剣 extends Weapon {
	public 火炎の剣() {
		super("火炎の剣", 80, "ターン終了時、火の玉を1個手に入れる", 5);
	}

	public void attack(GameService battle) {
		super.attack(battle);
		battle.getPlayer().setItem(new 火の玉());
	}

	public void weekAttack(GameService battle) {
		super.weekAttack(battle);
		battle.getPlayer().setItem(new 火の玉());
	}

	public void criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		battle.getPlayer().setItem(new 火の玉());
	}

	public void defence(GameService battle) {
		super.defence(battle);
		battle.getPlayer().setItem(new 火の玉());
	}

	public void tension(GameService battle) {
		super.tension(battle);
		battle.getPlayer().setItem(new 火の玉());
	}
}

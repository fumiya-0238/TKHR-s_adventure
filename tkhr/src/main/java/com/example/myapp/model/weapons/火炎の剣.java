package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.model.items.火の玉;

public class 火炎の剣 extends Weapon {
	public 火炎の剣() {
		super("火炎の剣", 80, "ターン終了時、火の玉を1個手に入れる", 5);
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		battle.getPlayer().setItem(new 火の玉());
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		battle.getPlayer().setItem(new 火の玉());
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		battle.getPlayer().setItem(new 火の玉());
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		battle.getPlayer().setItem(new 火の玉());
	}

	@Override
	public void tension(Battle battle, ActionInfo info) {
		super.tension(battle, info);
		battle.getPlayer().setItem(new 火の玉());
	}
}

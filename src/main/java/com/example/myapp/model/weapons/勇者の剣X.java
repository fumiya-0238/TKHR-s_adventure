package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 勇者の剣X extends Weapon {
	public 勇者の剣X() {
		super("勇者の剣X", 100, "強攻撃回数+1。ためると気合が50%上がる。ターン終了時、ボーナスターン-1", 17);
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		battle.getMonster().setTurn(-1);
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		battle.getMonster().setTurn(-1);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		battle.getMonster().setTurn(-1);
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		battle.getMonster().setTurn(-1);
	}

	@Override
	public void tension(Battle battle, ActionInfo info) {
		battle.getPlayer().setTension(50);
		battle.getMonster().setTurn(-1);
	}
}
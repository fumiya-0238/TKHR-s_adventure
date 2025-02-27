package com.example.myapp.model.weapons;

import tkhr.Battle;

public class 勇者の剣X extends Weapon {
	public 勇者の剣X() {
		super("勇者の剣X",100,"強攻撃回数+1。ためると気合が50%上がる。ターン終了時、ボーナスターン-1",17);
	}
	public void attack(GameService battle) {
		super.attack(battle);
		battle.getMonster().setTurn(-1);
	}

	public void weekAttack(GameService battle) {
		super.weekAttack(battle);
		battle.getMonster().setTurn(-1);
	}

	public void criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		battle.getMonster().setTurn(-1);
	}

	public void defence(GameService battle) {
		super.defence(battle);
		battle.getMonster().setTurn(-1);
	}

	public void tension(GameService battle) {
		battle.getPlayer().setTension(50);
		battle.getMonster().setTurn(-1);
	}
}
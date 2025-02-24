package tkhr.weapons;

import tkhr.Battle;
import tkhr.items.火の玉;

public class 火炎の剣 extends Weapon {
	public 火炎の剣() {
		super("火炎の剣", 80, "ターン終了時、火の玉を1個手に入れる", 5);
	}

	public void attack(Battle battle) {
		super.attack(battle);
		battle.setItem(new 火の玉());
	}

	public void weekAttack(Battle battle) {
		super.weekAttack(battle);
		battle.setItem(new 火の玉());
	}

	public void criticalAttack(Battle battle) {
		super.criticalAttack(battle);
		battle.setItem(new 火の玉());
	}

	public void defence(Battle battle) {
		super.defence(battle);
		battle.setItem(new 火の玉());
	}

	public void tension(Battle battle) {
		super.tension(battle);
		battle.setItem(new 火の玉());
	}
}

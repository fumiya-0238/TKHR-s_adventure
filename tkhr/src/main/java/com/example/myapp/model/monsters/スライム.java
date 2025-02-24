package tkhr.monsters;

import tkhr.Battle;
import tkhr.monsters.actions.AttackActionList;

public class スライム extends Monster {
	public スライム(int ID) {
		name = "スライム";
		HP = 3;
		OverHP = 5;
		ATK = 2;
		EXP = 2;
		Gold = 8;
		Turn = 3;
		this.ID = ID;
		super.init();
	}

	@Override
	public void actions(Battle battle) {
		AttackActionList.INSTANCE.normalAttack(battle);
	}

	@Override
	public void death(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		super.standardDeath(battle);
	}

	@Override
	public int simulateDamage(int damage, boolean penetrate, Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		return damage;
	}
}

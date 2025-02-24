package tkhr.monsters;

import tkhr.Battle;
import tkhr.battleScreen.BattleInterface;

public class クソウサギ extends Monster {
	public クソウサギ(int ID) {
		name="クソウサギ";
		HP=7;
		OverHP=11;
		ATK=3;
		EXP=5;
		Gold=14;
		Turn=4;
		this.ID=ID;
	}
	public void turn(BattleInterface bi,Battle battle) {
	}
	public int calcDamage(int damage,int action) {
		setDamage(damage);
		return damage;
	}
	public void death(BattleInterface bi) {
		bi.removeMonster();
		death=true;
	}
}

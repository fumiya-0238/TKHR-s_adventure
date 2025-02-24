package tkhr.monsters;

import tkhr.Battle;
import tkhr.battleScreen.BattleInterface;

public class ネペント extends Monster{
	public ネペント(int ID) {
		name="ネペント";
		HP=21;
		OverHP=29;
		ATK=8;
		EXP=7;
		Gold=16;
		Turn=4;
		this.ID=ID;
	}
	public void turn(BattleInterface bi,Battle battle) {
		normalAttack(bi);
	}
	public int calcDamage(int damage) {
		setDamage(damage);
		return damage;
	}
}

package tkhr.monsters;

import tkhr.Battle;
import tkhr.battleScreen.BattleInterface;
import tkhr.monsters.actions.ついばむ;
import tkhr.monsters.actions.通常攻撃;

public class とんびくん extends Monster implements 通常攻撃,ついばむ{
	public とんびくん(int ID) {
		name="とんびくん";
		HP=35;
		OverHP=52;
		ATK=8;
		EXP=100;
		Gold=5;
		Turn=5;
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

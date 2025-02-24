package tkhr.monsters;

import tkhr.Battle;
import tkhr.battleScreen.BattleInterface;

public class 暴走培養 extends Monster {
	public 暴走培養(int ID) {
		name="暴走培養";
		HP=102;//3
		OverHP=120;
		ATK=14;
		EXP=16;
		Gold=26;
		Turn=10;
		this.ID=ID;
		//super("スライム",3, 5, 2, 2, 8, 3, ID);
	}
	public void turn(BattleInterface bi,Battle battle) {
		normalAttack(bi,battle);
	}
	public int calcDamage(int damage,int action) {
		setDamage(damage);
		return damage;
	}
}
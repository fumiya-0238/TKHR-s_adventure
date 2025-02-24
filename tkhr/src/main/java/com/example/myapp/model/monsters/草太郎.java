package tkhr.monsters;

import tkhr.Battle;

public class 草太郎 extends Monster{
	public 草太郎(int ID) {
		name="草太郎";
		HP=6;
		OverHP=10;
		ATK=3;
		EXP=4;
		Gold=10;
		Turn=4;
		this.ID=ID;
	}

	@Override
	public int simulateDamage(int damage, boolean penetrate, Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		return damage;
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void death(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}

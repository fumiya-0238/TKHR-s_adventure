package tkhr.monsters;

import tkhr.Battle;
import tkhr.monsters.actions.AttackActionList;

public class 生首培養 extends Monster{
	public 生首培養(int ID) {
		name="生首培養";
		HP=17;
		OverHP=24;
		ATK=6;
		EXP=6;
		Gold=15;
		Turn=4;
		this.ID=ID;
		super.init();
	}
	
	@Override
	public int simulateDamage(int damage, boolean penetrate, Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if(battle.getPlayer().getAction()==3) {
		Monster m = new 暴走培養(34);
		HP=m.getHP()-MAXHP+HP;
		MAXHP=m.MAXHP;
		ATK=m.ATK;
		EXP=m.EXP;
		Gold=m.Gold;
		Turn=m.Turn;
		}
		return damage;
	}
	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
			AttackActionList.INSTANCE.normalAttack(battle);
	}
	@Override
	public void death(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}

package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;
import tkhr.items.薬草;

public class 世界樹の剣 extends Weapon{
	public 世界樹の剣() {
		super("世界樹の剣",100,"ターン終了時、薬草を1個手に入れる。強攻撃をするとアイテム欄の薬草を全て消滅し、消滅した数×5ダメージ追加",7);
		
	}
	@Override
	public void attack(Battle battle) {
		super.attack(battle);
		battle.setItem(new 薬草());
	}
	@Override
	public void weekAttack(Battle battle) {
		super.weekAttack(battle);
		battle.setItem(new 薬草());
	}
	@Override
	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		battle.getMonster().calcDamageResult((int)(player.getATK()*1.5)+battle.getPlayer().countItem("薬草")*5,(player.amountPlayerCondition("研ぎ石状態") > 0),battle);
	/*	public void removeItem(int i) {
			player.removeItem(i);
			notifyObservers();
		}*/
		battle.setItem(new 薬草());
	}
	@Override
	public void defence(Battle battle) {
		super.defence(battle);
		battle.setItem(new 薬草());
	}
	@Override
	public void tension(Battle battle) {
		super.tension(battle);
		battle.setItem(new 薬草());
	}
}

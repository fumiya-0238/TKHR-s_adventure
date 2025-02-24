package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;

public class タイタンブレード extends Weapon {
	public タイタンブレード() {
		super("タイタンブレード", 100, "強攻撃をしたターン、同時に防御もする。", 10);
	}
	
	@Override
	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
		super.defence(battle);
	}
}

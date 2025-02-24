package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;

public class 強強剣 extends Weapon {
	public 強強剣() {
		super("強強剣", 65, "強攻撃の倍率が1.5倍から2倍になる", 3);
	}

	@Override
	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() * 2;
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}
}

package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;

public class 貫通剣 extends Weapon {
	public 貫通剣() {
		super("貫通剣", 70, "全ての攻撃が貫通攻撃になる", 6);
	}

	public void attack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK();
		battle.getMonster().calcDamageResult(damage, true, battle);
	}

	public void weekAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		battle.getMonster().calcDamageResult(damage, true, battle);
	}

	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		battle.getMonster().calcDamageResult(damage, true, battle);
	}
}

package tkhr.weapons;

import tkhr.Battle;
import tkhr.Player;

public class ディレイソード extends Weapon {
	private int delay;
	public ディレイソード() {
		super("ディレイソード", 70, "攻撃しても相手のHPが減らずにダメージが蓄積される。防御をすると、それまで蓄積されていた分のダメージを与える。", 12);
	}
	
	public void attack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK();
		delay+=battle.getMonster().simulateDamage(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void weekAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = player.getATK() - 1;
		delay+=battle.getMonster().simulateDamage(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void criticalAttack(Battle battle) {
		Player player = battle.getPlayer();
		int damage = (int) (player.getATK() * 1.5);
		player.setCritical(-1);
		delay+=battle.getMonster().simulateDamage(damage, (player.amountPlayerCondition("研ぎ石状態") > 0), battle);
	}

	public void defence(Battle battle) {
		super.defence(battle);
		battle.getMonster().calcDamageResult(delay, true, battle);
		delay=0;
	}
}

package tkhr.weapons;

import tkhr.Battle;
import tkhr.monsters.Monster;

public class 死神の鎌 extends Weapon {
	public 死神の鎌() {
		super("死神の鎌", 90, "強攻撃した13ターン後に、敵が倒れる。その時敵のHPが5以下だとオーバーキル", 7);
	}

	public void criticalAttack(Battle battle) {
		super.criticalAttack(battle);
		Monster monster = battle.getMonster();
		if (monster.amountPlayerCondition("死神の呪い") == 0) {
			monster.plusMonsterCondition("死神の呪い",13);
		}
	}
}

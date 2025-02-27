package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.conditions.CreateCondition;

public class 死神の鎌 extends Weapon {
	public 死神の鎌() {
		super("死神の鎌", 90, "強攻撃した13ターン後に、敵が倒れる。その時敵のHPが5以下だとオーバーキル", 7);
	}

	public ActionInfo criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		Monster monster = battle.getMonster();
		if (monster.amountCondition(CreateCondition.CURSE) == 0) {
			monster.plusCondition(CreateCondition.CURSE);
		}
	}
}

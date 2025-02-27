package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.model.conditions.CreateCondition;

public class 死神の鎌 extends Weapon {
	public 死神の鎌() {
		super("死神の鎌", 90, "強攻撃した13ターン後に、敵が倒れる。その時敵のHPが5以下だとオーバーキル", 7);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		battle.getMonster().plusCondition(CreateCondition.CURSE);
	}
}

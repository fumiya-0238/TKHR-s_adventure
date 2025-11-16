package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 手加減無効 extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos) {
		int action = battle.getPlayer().getAction();
		if (getAmount() == 1 && action == 2 && !infos.get(0).getPenetrate()) {
			battle.conditionMessage(living, name);
			infos.get(0).setNumber(0);
		}
	}
}

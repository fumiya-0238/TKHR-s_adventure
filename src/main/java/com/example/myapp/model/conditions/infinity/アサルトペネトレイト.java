package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アサルトペネトレイト extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void attack(Battle battle, Living living, List<ActionInfo> infos) {
		if (24 <= battle.getMonster().getFinalAttack()) {
			infos.get(0).setPenetrate(true);
			battle.conditionMessage(living, name);
		}
	}
}

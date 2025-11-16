package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 攻撃力2倍 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.plusAttackMulti(2);
		turn = 1;
		battle.conditionMessage(living, name);
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.plusAttackMulti(0.5);
	}

}

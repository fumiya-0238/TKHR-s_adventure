package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class バリア状態 extends Condition {
	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (!nowInfo.getPenetrate()) {
			battle.conditionMessage(living, name);
			nowInfo.setNumber((int) (nowInfo.getNumber() / Math.pow(2, amount)));
		}
	}
}

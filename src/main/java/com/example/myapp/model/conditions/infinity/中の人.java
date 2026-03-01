package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 中の人 extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (nowInfo.getPenetrate()) {
			nowInfo.addMessages("状態発動:<" + name + ">", living);
			battle.conditionMessage(living, name);
			nowInfo.setNumber(nowInfo.getNumber() * 2);
		}
	}
}

package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 天聖の加護 extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
		amount = 50;
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (!nowInfo.getPenetrate()) {
			nowInfo.addMessages("状態発動:<" + name + ">", living);
			battle.conditionMessage(living, name);
			nowInfo.setNumber((int) (nowInfo.getNumber() / 2));
		}
	}

	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		amount -= nowInfo.getFinalNumber();
		if (amount <= 0) {
			conditionEnd = true;
		}
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.plusCondition(battle, infos, ConditionEnum.天聖の休息);
	}
}

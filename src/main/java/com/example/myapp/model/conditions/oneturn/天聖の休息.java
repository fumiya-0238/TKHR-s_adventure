package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 天聖の休息 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 20;
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
	//	int[] status = { 0, -1, 50, 0 };
		living.plusCondition(battle, infos, ConditionEnum.天聖の加護);
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 眼チャージ extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		battle.getMonster().plusCondition(battle, infos, ConditionEnum.眼チャージ);
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 消化液 extends Action{
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.消化液);
	}
}

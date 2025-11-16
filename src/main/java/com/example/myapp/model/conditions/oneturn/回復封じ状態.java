package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 回復封じ状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 2;
		amount = 1;
	}

	@Override
	public void calcHeal(Battle battle, Living living, List<ActionInfo> infos) {
		infos.get(0).setNumber(0);
		String message = living.getName() + "の回復は封じられている";
		battle.conditionMessage(living, name);
		infos.get(0).addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
	}
}

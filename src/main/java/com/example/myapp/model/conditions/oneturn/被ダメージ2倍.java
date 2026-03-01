package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 被ダメージ2倍 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 2;
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		battle.conditionMessage(living, name);
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		nowInfo.setNumber(nowInfo.getNumber() * 2);
	}
}

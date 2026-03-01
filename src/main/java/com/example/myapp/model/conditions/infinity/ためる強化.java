package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ためる強化 extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.addMessages("状態発動:<" + name + ">", battle.getPlayer());
		battle.conditionMessage(battle.getPlayer(), name);
		nowInfo.setNumber(50);
	}
}

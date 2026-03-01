package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 強攻撃強化 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	public void newCondition(Living living) {
		setInfinity();
		amount = 1;
	}

	@Override
	public void criticalAttack(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		// TODO 自動生成されたメソッド・スタブ
		battle.conditionMessage(living, name);
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		nowInfo.setNumber(nowInfo.getNumber() * 2 / 1.5);
	}
}

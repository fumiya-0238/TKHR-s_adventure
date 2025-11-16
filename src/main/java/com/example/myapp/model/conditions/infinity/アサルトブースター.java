package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アサルトブースター extends Condition {

	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos) {
		if (battle.getPlayer().getAction() == 2) {
			battle.getMonster().plusAttackPlus(-amount);
			amount = 0;
			battle.conditionMessage(living, name);
		}
	}

	@Override
	public void turnEnd(Battle battle, Living living, List<ActionInfo> infos) {
		amount += 2;
		battle.getMonster().plusAttackPlus(2);
		battle.conditionMessage(living, name);
	}
}

package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ディレイモード extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void delayMode(Battle battle, Living living, List<ActionInfo> infos) {
		battle.conditionMessage(living, name);
		ActionInfo info = infos.get(0);
		amount += info.getFinalNumber();
		info.setNumber(0);
	}

	@Override
	public void defence(Battle battle, Living living, List<ActionInfo> infos) {
		battle.conditionMessage(living, name);
		ActionInfo info = new ActionInfo();
		info.setNumber(amount);
		info.setPenetrate(true);
		info.setActionType(1);
		info.setReceiver(battle.getMonster());
		infos.add(info);
		amount = 0;
	}
}

package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ボーナスバリア extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
	}

	@Override
	public void calcDamagePlus(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (!nowInfo.getPenetrate()) {
			battle.conditionMessage(living, name);
			nowInfo.setNumber(nowInfo.getNumber() - ((Monster) living).getTurn());
			nowInfo.addMessages("状態発動:<" + name + ">", living);
		}
	}
}

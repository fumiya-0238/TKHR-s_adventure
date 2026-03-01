package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ダークアーマー extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void calcDamagePlus(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		int dark = battle.getPlayer().countItem(21);
		if (!nowInfo.getPenetrate() && 0 < dark) {
			nowInfo.addMessages("状態発動:<" + name + ">", living);
			battle.conditionMessage(living, name);
			nowInfo.setNumber(nowInfo.getNumber() - dark);
		}
	}
}
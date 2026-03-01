package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 根性 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> info) {
		// TODO 自動生成されたメソッド・スタブ
		newCondition(living);
	}

	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
		nonDelete = true;
	}

	@Override
	public void death(Battle battle, Living living, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		if (living instanceof Monster) {
			int subtraction = ((Monster) living).getOverHP() - living.getHP();
			((Monster) living).setOverHP(1 + subtraction);
		}
		ActionInfo info = new ActionInfo();
		info.addMessages("状態発動:<" + name + ">", living);
		info.addMessages("HPが1で耐えた", living);
		infos.add(info);
		living.setHP(1);
		battle.conditionMessage(living, name);
	}
}

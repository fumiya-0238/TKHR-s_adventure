package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateMonster;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 暴走スイッチ extends Condition {
	@Override
	public void newCondition(Battle battle, Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void calcDamage(Battle battle, Living living, List<ActionInfo> infos, int n) {
		if(amount==0) {
			return;
		}
		int action = battle.getPlayer().getAction();
		if (getAmount() == 1 && action == 3) {
			battle.setMonster(CreateMonster.INSTANCE.create(34));
		}
	}
}

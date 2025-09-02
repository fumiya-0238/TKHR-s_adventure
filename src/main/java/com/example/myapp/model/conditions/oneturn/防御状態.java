package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 防御状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos, int n) {
		super.newCondition(battle, living, infos, n);
		int amount = living.amountCondition(ConditionEnum.防御強化);
		int percent = living.getMAXHP() / 10;
		ActionInfo info = new ActionInfo();
		info.setDamage(percent * (int) (Math.pow(2, amount)));
		infos.add(info);
		living.calcHeal(battle, infos, n);
	}

	@Override
	public void calcDamage(Battle battle, Living living, List<ActionInfo> infos, int n) {
		if (amount == 0) {
			return;
		}
		if (!infos.get(n).getPenetrate()) {
			infos.get(n).setDamage(0);
		}
	}
}

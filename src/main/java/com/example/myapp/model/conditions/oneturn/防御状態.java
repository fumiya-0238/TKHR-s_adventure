package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 防御状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		super.newCondition(battle, living, infos);
		battle.conditionMessage(living, name);
		int amount = living.amountCondition(ConditionEnum.防御強化);
		int percent = 2 + amount;
		ActionInfo info = new ActionInfo();
		info.setActionType(2);
		info.setReceiver(living);
		info.setNumber(living.getMAXHP() * percent / 10);
		if (0 < lv) {
			info.setNumber(living.getMAXHP());
		}
		//info.setNumber(percent * (int) (Math.pow(2, amount)));
		infos.add(info);
		//living.calcHeal(battle, infos);
	}

	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos) {
		if (!infos.get(0).getPenetrate()) {
			infos.get(0).setNumber(0);
			Battle.addLogs(new ScreenChange(ScreenEnum.防御成功, ""));
		}
	}
}

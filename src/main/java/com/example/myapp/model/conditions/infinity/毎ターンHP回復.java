package com.example.myapp.model.conditions.infinity;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class 毎ターンHP回復 extends Condition {

	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

	@Override
	public void setLevel(int[] status) {
		lv = status[0];
		name = "毎ターンHP" + lv + "回復";
	}

	@Override
	public void turnEnd(Battle battle, Living living, List<ActionInfo> infos) {
		if (0 == living.getHP()) {
			return;
		}
		//Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		battle.conditionMessage(living, name);
		super.turnEnd(battle, living, infos);
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(lv);
		info.setReceiver(living);
		info.setActionType(ActionTypeEnum.回復);
		infos2.add(info);
		infos.addAll(infos2);
	}
}

package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class ディレイモード extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void delayMode(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		battle.conditionMessage(living, name);
		amount += nowInfo.getFinalNumber();
		nowInfo.setNumber(0);
		nowInfo.addMessages("状態発動:<" + name + ">", living);
	}

	@Override
	public void defence(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		battle.conditionMessage(living, name);
		ActionInfo info = new ActionInfo();
		info.setNumber(amount);
		info.setPenetrate(true);
		info.setActionType(ActionTypeEnum.ダメージ);
		info.setReceiver(battle.getTargetMonster());
		infos.add(info);
		amount = 0;
	}
}

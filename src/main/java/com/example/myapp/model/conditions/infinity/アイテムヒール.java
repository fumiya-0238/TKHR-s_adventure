package com.example.myapp.model.conditions.infinity;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class アイテムヒール extends Condition {
	@Override
	public void useItem(Battle battle, Living living, List<ActionInfo> infos) {
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.addMessages("状態発動:<" + name + ">", living);
		info.setNumber(3);
		info.setReceiver(living);
		info.setActionType(ActionTypeEnum.回復);
		infos2.add(info);
		battle.conditionMessage(living, name);
		infos.addAll(infos2);
	}
}

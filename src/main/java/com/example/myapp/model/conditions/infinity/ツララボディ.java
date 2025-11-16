package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ツララボディ extends Condition {
	@Override
	public void counter(Battle battle, Living receiver, List<ActionInfo> infos) {
		int percent = receiver.getHP() * 100 / receiver.getMAXHP();
		if (percent <= 50) {
			infos.remove(0);
			battle.conditionMessage(receiver, name);
			CreateAction.INSTANCE.create(1).actionEffect(battle, infos);
		}
	}
}

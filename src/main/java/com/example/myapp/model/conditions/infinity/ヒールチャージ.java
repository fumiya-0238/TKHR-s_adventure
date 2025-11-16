package com.example.myapp.model.conditions.infinity;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ヒールチャージ extends Condition {

	@Override
	public void newCondition(Living living) {
		setInfinity();
		amount = 1;
	}

	@Override
	public void setHeal(Battle battle, Living living, List<ActionInfo> infos) {
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(25);
		infos2.add(info);
		battle.conditionMessage(living, name);
		((Player) living).plusTension(battle, infos2);
		infos.addAll(infos2);
	}
}

package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 死神の呪い extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		setTurn(13);
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
				living.setDamage(living.getHP());
	}
}

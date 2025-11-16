package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 貫通 extends Condition {
	@Override
	public void attack(Battle battle, Living living, List<ActionInfo> infos) {
		infos.get(0).setPenetrate(true);
		//battle.conditionMessage(living, name);
	}
}

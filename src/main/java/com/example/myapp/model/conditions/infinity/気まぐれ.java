package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 気まぐれ extends Condition {
	@Override
	public void newCondition(Living living) {
		setInfinity();
		living.setAttack(living.getHP() % 10 * 10);
	}

	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		living.setAttack(living.getHP() % 10 * 10);
		battle.conditionMessage(living, name);
	}

	@Override
	public void setHeal(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		living.setAttack(living.getHP() % 10 * 10);
		battle.conditionMessage(living, name);
	}
}

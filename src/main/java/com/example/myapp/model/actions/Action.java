package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class Action {
	protected String name;
	protected boolean attackIs;

	public void setStatus(String name, boolean attackIs) {
		this.name = name;
		this.attackIs = attackIs;
	}

	public void setStatus(int[] status) {
	}

	protected abstract void doAction(Battle battle, Monster monster, List<ActionInfo> infos);

	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	public void actionEffect(Battle battle, Monster monster, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター行動エフェクト, String.valueOf(monster.getNumber())));
		doAction(battle, monster, infos);
		battle.commonAction(infos);
	}
/*
	public void commonAttack(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		//Monster monster = battle.getMonster();
		List<Condition> conditions = monster.getConditions();
		for (Condition condition : conditions) {
			condition.attack(battle, monster, infos, nowInfo);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, monster, infos, nowInfo);
		}
		
	}*/

	public String getName() {
		return name;
	}

	public boolean isAttackIs() {
		return attackIs;
	}
}

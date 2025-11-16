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
	
	protected abstract void doAction(Battle battle, List<ActionInfo> infos);
	
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {	
	}
	
	public void actionEffect(Battle battle, List<ActionInfo> infos) {
		doAction(battle,infos);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター行動エフェクト,""));
		battle.commonAction(infos);
	}
	
	public void commonAttack(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		
		List<Condition> conditions = monster.getConditions();
		for (Condition condition : conditions) {
			condition.attack(battle, monster, infos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, monster, infos);
		}
		/*
		for (Condition condition : battle.getPlayer().getConditions()) {
			condition.calcDamage(battle, battle.getPlayer(), infos);
		}*/
	}
	
	public String getName() {
		return name;
	}

	public boolean isAttackIs() {
		return attackIs;
	}
}

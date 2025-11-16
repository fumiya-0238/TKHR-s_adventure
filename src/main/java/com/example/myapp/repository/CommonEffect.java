package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;

public class CommonEffect {
	public void commonMonsterAttack(Battle battle, double damage, boolean penetrateIs, String message,
			List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		Monster monster = battle.getMonster();
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		infos2.add(info);
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		info.setAttacker(monster);
		info.setReceiver(battle.getPlayer());
		info.setActionType(5);
		info.setPenetrate(penetrateIs);
		info.setNumber(damage);
		List<Condition> conditions = monster.getConditions();
		for (Condition condition : conditions) {
			condition.attack(battle, monster, infos2);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, monster, infos2);
		}
		infos.addAll(infos2);
	}
	public void commonDamege(Battle battle,Living receiver, int damage, boolean penetrateIs,
			List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(damage);
		info.setReceiver(receiver);
		info.setPenetrate(penetrateIs);
		info.setActionType(1);
		infos2.add(info);
		infos.addAll(infos2);
	}
	
	public void commonHeal(Living receiver, int heal, List<ActionInfo> infos) {
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(heal);
		info.setReceiver(receiver);
		info.setActionType(2);
		infos2.add(info);
		infos.addAll(infos2);
	}
}

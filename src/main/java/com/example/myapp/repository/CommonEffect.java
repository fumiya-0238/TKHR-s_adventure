package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;

public class CommonEffect {
	public void commonMonsterAttack(Battle battle, Monster monster, double attack, boolean penetrateIs, String message,
			List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();

		info.addMessages(message, monster);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		info.setAttacker(monster);
		info.setReceiver(battle.getPlayer());
		info.setActionType(ActionTypeEnum.モンスターの攻撃);
		info.setPenetrate(penetrateIs);
		info.setNumber(attack);
		List<Condition> conditions = monster.getConditions();
		for (Condition condition : conditions) {
			condition.attack(battle, monster, infos2, info);
		}

		for (Condition condition : conditions) {
			condition.damagePlus(battle, monster, infos2, info);
		}
		if (battle.getDungeon().isRandomMode()) {
			double x = 0.8 + Math.random() * 0.2;
			info.setNumber(info.getNumber() * x);
		}
		conditions.removeIf(condition -> condition.isConditionEnd());
		infos2.add(0, info);
		infos.addAll(infos2);
	}

	public void commonDamege(Battle battle, ActionTypeEnum type, Living receiver, int damage, boolean penetrateIs,
			List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(damage);
		info.setReceiver(receiver);
		info.setPenetrate(penetrateIs);
		info.setActionType(type);
		infos2.add(info);
		infos.addAll(infos2);
	}

	public void commonHeal(Living receiver, int heal, List<ActionInfo> infos) {
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(heal);
		info.setReceiver(receiver);
		info.setActionType(ActionTypeEnum.回復);
		infos2.add(info);
		infos.addAll(infos2);
	}
}

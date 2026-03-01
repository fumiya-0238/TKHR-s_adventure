package com.example.myapp.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class A38追加攻撃 extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		info.setAttacker(monster);
		info.setReceiver(battle.getPlayer());
		info.setActionType(ActionTypeEnum.カウンター);
		info.setPenetrate(false);
		info.setNumber(monster.getFinalAttack());
		List<Condition> conditions = monster.getConditions();
		for (Condition condition : conditions) {
			condition.attack(battle, monster, infos2, info);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, monster, infos2, info);
		}
		infos2.add(0, info);
		infos.addAll(infos2);
	}
}

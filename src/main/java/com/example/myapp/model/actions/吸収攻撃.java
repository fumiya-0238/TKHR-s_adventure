package com.example.myapp.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 吸収攻撃 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		List<ActionInfo> healInfos = new ArrayList<>();
		ActionInfo healInfo = new ActionInfo();
		healInfo.setNumber(infos.get(0).getNumber() / 2);
		healInfo.setActionType(2);
		Monster monster = battle.getMonster();
		healInfo.setReceiver(monster);
		healInfos.add(healInfo);
		for (Condition condition : monster.getConditions()) {
			condition.drain(battle, monster, healInfos);
		}
		infos.addAll(healInfos);
	}
}

package com.example.myapp.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class A05吸収攻撃 extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		List<ActionInfo> healInfos = new ArrayList<>();
		ActionInfo healInfo = new ActionInfo();
		healInfo.setNumber(nowInfo.getFinalNumber() / 2);
		healInfo.setActionType(ActionTypeEnum.回復);
		healInfo.setReceiver(monster);
		for (Condition condition : monster.getConditions()) {
			condition.drain(battle, monster, healInfos, healInfo);
		}
		healInfos.add(0, healInfo);
		infos.addAll(healInfos);
	}
}

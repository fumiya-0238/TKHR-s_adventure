package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class A19クリスタルレーザー extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack() * 2, true, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		monster.plusCondition(battle, infos, ConditionEnum.ボーナスターン保持);
	}
}
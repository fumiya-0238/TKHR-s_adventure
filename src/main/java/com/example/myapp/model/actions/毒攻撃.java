package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 毒攻撃 extends Action {
	private int lv;

	@Override
	public void setStatus(int[] status) {
		lv = status[0];
		name = name.replace("<n>", String.valueOf(lv));
	}

	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack(), false, message, infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		int[] status = { lv };
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.毒, status);
	}
}

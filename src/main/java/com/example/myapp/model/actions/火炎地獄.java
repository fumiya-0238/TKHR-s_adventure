package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 火炎地獄 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, (int) (monster.getFinalAttack() * 1.5), false, message,
				infos);
		
	}
	
	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		int[] status = { 6 };
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.毒, status);
		
	}
}
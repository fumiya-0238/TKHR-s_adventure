package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class A28自爆 extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		int percent = monster.getHP() * 100 / monster.getMAXHP();
		if (percent <= 50) {
			battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack() * 4, false, message,
					infos);
			monster.plusBonus();
		} else {
			battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack() * 2, false, message,
					infos);
		}
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, monster, monster.getHP(), true, infos);
	}
}

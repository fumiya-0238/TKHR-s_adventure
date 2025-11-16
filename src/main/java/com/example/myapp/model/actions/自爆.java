package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 自爆 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		int percent = monster.getHP() * 100 / monster.getMAXHP();
		if (percent <= 50) {
			battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack() * 2, false, message, infos);
		} else {
			battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack() * 4, false, message, infos);
			battle.getPlayer().plusBonus();
		}
		battle.getCommonEffect().commonDamege(battle, monster, monster.getHP(), true, infos);
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ブレイクパンチ extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle,monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().weaponReset();
	}
}

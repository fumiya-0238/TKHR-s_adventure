package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 超コカトリスの災い extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		int n = battle.getMonster().getHP() % 5;
		battle.getPlayer().setCommandFalse(n);
		Battle.addLogs(new ScreenChange(ScreenEnum.コカトリスイッチ, String.valueOf(n)));
		n = (battle.getMonster().getHP() + 1) % 5;
		battle.getPlayer().setCommandFalse(n);
		Battle.addLogs(new ScreenChange(ScreenEnum.コカトリスイッチ, String.valueOf(n)));
	}

}

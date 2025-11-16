package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 真珠飛ばし extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, (int) (monster.getFinalAttack() * 1.5), false, message,
				infos);
		battle.getCommonEffect().commonDamege(battle, monster, 5, true, infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(14));
	}
}

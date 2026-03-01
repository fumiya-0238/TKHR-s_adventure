package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class A12真珠飛ばし extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack() * 1.5, false, message,
				infos);
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, monster, 5, true, infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		battle.getPlayer().addItem(CreateItem.INSTANCE.create(13));
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class A17マタードレイン extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack(), false, message, infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		Player player = battle.getPlayer();
		ActionInfo healInfo = new ActionInfo();
		healInfo.setNumber(player.countItem(21));
		healInfo.setReceiver(monster);
		healInfo.setActionType(ActionTypeEnum.回復);
		infos.add(healInfo);
	}
}
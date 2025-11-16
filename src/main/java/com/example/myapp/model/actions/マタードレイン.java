package com.example.myapp.model.actions;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class マタードレイン extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		ActionInfo info = new ActionInfo();
		infos.add(info);
		String message = battle.getMonster().getName() + "は" + name + "をした";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		info.setAttacker(battle.getMonster());
		info.setReceiver(battle.getPlayer());
		info.setActionType(5);
		int damage = battle.getMonster().getFinalAttack();
		info.setNumber(damage);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(player.countItem("ダークマター"));
		info.setReceiver(battle.getMonster());
		info.setActionType(2);
		infos2.add(info);
		infos.addAll(infos2);
	}
}

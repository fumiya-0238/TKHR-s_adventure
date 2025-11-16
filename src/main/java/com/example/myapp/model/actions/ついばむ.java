package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ついばむ extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "の" + name;
		battle.getCommonEffect().commonMonsterAttack(battle, monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		int n = player.descSearchItem("草");
		if (n == -1) {
			return;
		}
		Item item = player.getItems().get(n);
		ActionInfo glassInfo = new ActionInfo();
		glassInfo.setReceiver(battle.getMonster());
		switch (item.getName()) {
		case "薬草":
			glassInfo.setNumber(10);
			glassInfo.setActionType(2);
			break;
		case "アロエ草":
			glassInfo.setNumber(30);
			glassInfo.setActionType(2);
			break;
		case "ダメージ草":
			glassInfo.setNumber(10);
			glassInfo.setActionType(1);
			break;
		case "超ダメージ草":
			glassInfo.setNumber(30);
			glassInfo.setActionType(1);
			break;
		}
		player.removeItem(n);
		infos.add(glassInfo);
	}
}

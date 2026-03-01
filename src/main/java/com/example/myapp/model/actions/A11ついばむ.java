package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class A11ついばむ extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "の" + name;
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack(), false, message,
				infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		Player player = battle.getPlayer();
		int n = player.descSearchItem("草");
		if (n == -1) {
			return;
		}
		Item item = player.getItems().get(n);
		ActionInfo glassInfo = new ActionInfo();
		glassInfo.setReceiver(monster);
		switch (item.getName()) {
		case "薬草":
			glassInfo.setNumber(10);
			glassInfo.setActionType(ActionTypeEnum.回復);
			break;
		case "アロエ草":
			glassInfo.setNumber(30);
			glassInfo.setActionType(ActionTypeEnum.回復);
			break;
		case "ダメージ草":
			glassInfo.setNumber(10);
			glassInfo.setActionType(ActionTypeEnum.ダメージ);
			break;
		case "超ダメージ草":
			glassInfo.setNumber(30);
			glassInfo.setActionType(ActionTypeEnum.ダメージ);
			break;
		}
		player.removeItem(n);
		infos.add(glassInfo);
	}
}

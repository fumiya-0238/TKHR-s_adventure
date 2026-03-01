package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class A16ダークマタースラッシュ extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack(), false, message, infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		Player player = battle.getPlayer();
		player.addItem(CreateItem.INSTANCE.create(21), player.getMaxItem() - player.getItems().size());
	}
}

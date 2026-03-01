package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class A27アイテムキャッチ extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		int size = player.getItems().size();
		if (size > 0) {
			int price = battle.getPlayer().getItems().get(0).getPrice();
			player.removeItem(0);
			monster.plusAttackPlus(price);
		}
	}
}

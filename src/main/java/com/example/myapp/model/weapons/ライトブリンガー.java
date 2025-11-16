package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ライトブリンガー extends Weapon {
	@Override
	public void defence(Battle battle, List<ActionInfo> infos) {
		super.defence(battle, infos);
		Player player = battle.getPlayer();
		int size = player.getItems().size();
		if (size > 0) {
			player.removeItem(size - 1);
			player.setItem(CreateItem.INSTANCE.create(21));
		}
	}
}
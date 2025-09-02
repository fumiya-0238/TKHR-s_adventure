package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ライトブリンガー extends Weapon {
	@Override
	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		super.defence(battle, infos, n);
		Player player = battle.getPlayer();
		int size = player.getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(battle,size - 1);
			player.setItem(CreateItem.INSTANCE.create(21));
		}
	}
}
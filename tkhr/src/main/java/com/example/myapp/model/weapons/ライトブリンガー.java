package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;
import com.example.myapp.model.items.光の玉;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;

public class ライトブリンガー extends Weapon {
	public ライトブリンガー() {
		super("ライトブリンガー", 90, "防御をするとアイテム欄の1番下のアイテムが光の玉に変わる", 6);
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		Player player = battle.getPlayer();
		int size = player.getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
			player.setItem(new 光の玉());
		}
	}
}

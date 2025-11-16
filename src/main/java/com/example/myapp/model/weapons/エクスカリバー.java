package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class エクスカリバー extends Weapon {
	private void exGet(Battle battle) {
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(3));
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(5));
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(7));
	}

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		exGet(battle);
		return 0;
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		exGet(battle);
		return 0;
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		exGet(battle);
		return 0;
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos) {
		exGet(battle);
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos) {
		exGet(battle);
	}
}

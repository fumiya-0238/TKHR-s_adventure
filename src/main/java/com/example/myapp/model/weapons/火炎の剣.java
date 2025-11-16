package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 火炎の剣 extends Weapon {
	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
		return super.attack(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
		return super.weekAttack(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
		return super.criticalAttack(battle, infos);
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos) {
		super.defence(battle, infos);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos) {
		super.tension(battle, infos);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}
}

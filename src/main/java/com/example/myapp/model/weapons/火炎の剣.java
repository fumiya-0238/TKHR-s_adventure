package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 火炎の剣 extends Weapon {
	@Override
	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		super.attack(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}

	@Override
	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.weekAttack(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}

	@Override
	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.criticalAttack(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		super.defence(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		super.tension(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(2));
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 火を灯す extends Action{
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos ,int n) {
		infos.get(n).setLiving(battle.getPlayer());
		int damage = battle.getMonster().getFinalAttack();
		infos.get(n).setDamage(damage);
		infos.get(n).setPenetrate(true);
		battle.commonAction(info);
	}
}
package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 攻撃 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos, int n) {
		infos.get(n).setLiving(battle.getPlayer());
		int damage = battle.getMonster().getAttack();
		infos.get(n).setDamage(damage);
		commonAction(battle, infos, n);
	}
}

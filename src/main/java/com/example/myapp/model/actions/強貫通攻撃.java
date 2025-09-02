package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 強貫通攻撃 extends Action{
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos, int n) {
		infos.get(n).setLiving(battle.getPlayer());
		int damage = (int)(battle.getMonster().getAttack()*1.5);
		infos.get(n).setDamage(damage);
		infos.get(n).setPenetrate(true);
		commonAction(battle, infos, n);
	}
}

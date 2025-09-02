package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 草で回復 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos, int n) {
		infos.get(n).setLiving(battle.getMonster());
		infos.get(n).setDamage(3);
		commonAction(battle, infos, n);
	}

}

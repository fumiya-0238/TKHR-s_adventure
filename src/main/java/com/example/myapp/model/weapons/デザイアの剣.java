package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class デザイアの剣 extends Weapon {
	private void desire(Battle battle) {
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(battle, size - 1);
		}
	}

	@Override
	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		super.attack(battle, infos, n);
		desire(battle);
	}

	@Override
	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.weekAttack(battle, infos, n);
		desire(battle);
	}

	@Override
	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.criticalAttack(battle, infos, n);
		desire(battle);
	}
}

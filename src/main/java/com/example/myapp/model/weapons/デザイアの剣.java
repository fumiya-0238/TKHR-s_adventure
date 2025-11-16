package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class デザイアの剣 extends Weapon {
	private void desire(Battle battle) {
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
		}
	}

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		desire(battle);
		return super.attack(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		return super.weekAttack(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		return super.criticalAttack(battle, infos);
	}
}

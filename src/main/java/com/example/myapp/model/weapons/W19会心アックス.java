package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W19会心アックス extends Weapon {
	private int critical(Battle battle, List<ActionInfo> infos) {
		if (battle.getTargetMonster().getHP() % 3 == 0) {
			return battle.getPlayer().getFinalAttack() / 2;
		} else {
			return 0;
		}
	}

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		return critical(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		return critical(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		return critical(battle, infos);
	}
}
package com.example.myapp.model.weapons;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class デザイアの剣 extends Weapon {
	public デザイアの剣() {
		super("デザイアの剣", 100, "いずれかの攻撃をすると、アイテム欄の1番下の最後が消滅する", 17);
	}

	private void desire(Battle battle) {
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
		}
	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		desire(battle);
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		desire(battle);
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		desire(battle);
	}
}

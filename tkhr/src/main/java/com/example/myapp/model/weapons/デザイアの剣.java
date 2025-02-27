package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;

public class デザイアの剣 extends Weapon {
	public デザイアの剣() {
		super("デザイアの剣", 100, "いずれかの攻撃をすると、アイテム欄の1番下の最後が消滅する", 17);
	}

	public void attack(GameService battle) {
		super.attack(battle);
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
		}
	}

	public void weekAttack(GameService battle) {
		super.weekAttack(battle);
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
		}
	}

	public void criticalAttack(GameService battle) {
		super.criticalAttack(battle);
		int size = battle.getPlayer().getItems().size();
		if (size > 0) {
			battle.getPlayer().removeItem(size - 1);
		}
	}
}

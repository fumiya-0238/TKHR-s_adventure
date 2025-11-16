package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class スライム状態 extends Condition {
	private int maxHp;
	private int attack;

	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 2;
		amount = 1;
		int hp = living.getHP();
		maxHp = living.getMAXHP();
		attack = living.getAttack();
		battle.conditionMessage(living, name);
		living.setMAXHP(3);
		if (3 < hp) {
			living.setHP(3);
		} else {
			living.setHP(hp);
		}
		int weapon = 0;
		if (living instanceof Player) {
			Player player = battle.getPlayer();
			weapon = player.getWeapons().get(0).getAttack();
		}
		living.setAttack(2 + weapon);
	}

	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		living.setMAXHP(maxHp);
		living.setHP(living.getHP());
		living.setAttack(attack);
	}
}

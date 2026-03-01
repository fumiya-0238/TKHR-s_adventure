package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class バーサーク状態 extends Condition {
	private double berserkMulti = 1;

	private void berserk(Battle battle, Living living, List<ActionInfo> infos) {
		int percent = living.getHP() * 100 / living.getMAXHP();
		if (percent <= 10) {
			battle.getPlayer().plusAttackMulti(1 / berserkMulti);
			berserkMulti = 5;
			battle.getPlayer().plusAttackMulti(berserkMulti);
		} else if (percent <= 20) {
			battle.getPlayer().plusAttackMulti(1 / berserkMulti);
			berserkMulti = 2;
			battle.getPlayer().plusAttackMulti(berserkMulti);
		}
		battle.conditionMessage(living, name);
	}

	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		super.newCondition(battle, living, infos);
		berserk(battle, living, infos);
	}

	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		berserk(battle, living, infos);
	}

	@Override
	public void setHeal(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		berserk(battle, living, infos);
	}

	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		battle.getPlayer().plusAttackMulti(1 / berserkMulti);
	}
}

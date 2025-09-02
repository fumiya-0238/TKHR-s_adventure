package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class Action {
	private String name;
	private boolean attackIs;

	public void setStatus(String name, boolean attackIs) {
		this.name = name;
		this.attackIs = attackIs;
	}

	public abstract void doAction(Battle battle, List<ActionInfo> infos, int n);

	protected void commonAction(Battle battle, List<ActionInfo> infos, int n) {
		infos.get(n).setAttackIs(attackIs);
		for (int i = n; i <= n; i++) {
			ActionInfo info = infos.get(i);
			if (info.getAttackIs()) {
				info.getLiving().calcDamage(battle, infos, i);
			} else if (0 < info.getDamage()) {
				info.getLiving().calcHeal(battle, infos, i);
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
	}

	public String getName() {
		return name;
	}

	public boolean isAttackIs() {
		return attackIs;
	}
}

package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 手加減無効 extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	
	@Override
	public void calcDamage(Battle battle,Living living,ActionInfo info) {
		int action = battle.getPlayer().getAction();
		if (getAmount() == 1 && action == 2 && !info.getPenetrate()) {
			info.setDamage(0);
		}
	}
}

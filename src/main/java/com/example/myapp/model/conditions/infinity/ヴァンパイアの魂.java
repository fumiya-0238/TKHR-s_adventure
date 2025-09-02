package com.example.myapp.model.conditions.infinity;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ヴァンパイアの魂 extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	public void setDamage(Battle battle,Living living,ActionInfo info) {
	setAmount(getAmount()+info.getDamage());
	}
}

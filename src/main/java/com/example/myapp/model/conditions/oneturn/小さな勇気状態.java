package com.example.myapp.model.conditions.oneturn;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 小さな勇気状態 extends Condition {
	public void damage(Battle battle, Living living, ActionInfo info) {
		if (amount == 0) {
			return;
		}
		info.setNumber(info.getNumber() + getAmount() * 5);
	}
}

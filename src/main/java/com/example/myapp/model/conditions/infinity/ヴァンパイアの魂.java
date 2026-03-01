package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ヴァンパイアの魂 extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 0;
	}

	@Override
	public void drain(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
		nowInfo.addMessages("状態発動:<" + name + ">", living);
		amount += nowInfo.getNumber();
	}
}

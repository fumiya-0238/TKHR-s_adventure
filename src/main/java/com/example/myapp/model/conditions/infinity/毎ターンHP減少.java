package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 毎ターンHP減少 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	//バトル中コンディション発現
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos, int n) {
		setInfinity();
		amount = 1;
	}
	
	//最初コンディション発現
	public void newCondition(Living living) {
		setTurn("1");
		amount = 1;
	}
	//最初コンディション発現
	public void newCondition(Living living) {
		setTurn("1");
		amount = 1;
	}
	@Override
	public void turnEnd(Battle battle, Living living) {
		living.setDamage(getAmount());
	}
}

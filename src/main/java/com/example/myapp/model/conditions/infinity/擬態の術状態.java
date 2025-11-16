package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 擬態の術状態 extends Condition {
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	
	@Override
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos) {
		infos.get(0).setNumber(0);
	}
}

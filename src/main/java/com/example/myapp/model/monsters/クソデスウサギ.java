package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class クソデスウサギ extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		plusCondition(ConditionEnum.強い執念);
	}
	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		setAction(actions.get(0));	
	}
}

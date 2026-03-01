package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M33マッスルペンギン extends Monster {
	
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		plusCondition(ConditionEnum.手加減無効);
		int[] status = {1};
		plusCondition(ConditionEnum.毎ターンHP回復,status);
	}
	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ

	}
}

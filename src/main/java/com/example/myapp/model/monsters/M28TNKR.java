package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M28TNKR extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		int[] ltad = { 0, -1, 1 };
		plusCondition(ConditionEnum.バリア, ltad);
	}

	@Override
	public void actions(Battle battle) {
		setAction(actions.get(0));
	}
}

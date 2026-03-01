package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M06とんびくん extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(3));
		actions.add(CreateAction.INSTANCE.create(11));
		int[] status = { 1 };
		plusCondition(ConditionEnum.毎ターンHP回復, status);
	}

	@Override
	public void actions(Battle battle) {
		if (hp % 2 == 0) {
			setAction(actions.get(1));
		} else if (hp % 3 == 0) {
			setAction(actions.get(2));
		} else {
			setAction(actions.get(0));
		}
	}
}

package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M38ヴァンパイアシスター extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(5));
		actions.add(CreateAction.INSTANCE.create(34));
		plusCondition(ConditionEnum.ヴァンパイアの魂);
		int[] status = { 1 };
		plusCondition(ConditionEnum.毎ターンHP回復, status);
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (turn == 2 || turn == 3) {
			setAction(actions.get(2));
		} else if (hp % 4 != 0) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}

}

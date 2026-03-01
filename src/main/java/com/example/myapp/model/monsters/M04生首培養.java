package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M04生首培養 extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(2));
		//plusCondition(ConditionEnum.暴走スイッチ);
	}

	@Override
	public void actions(Battle battle) {
		if (getTurn() == 3 || getTurn() == 1) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

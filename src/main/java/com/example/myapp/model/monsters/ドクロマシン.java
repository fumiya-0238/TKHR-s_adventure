package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class ドクロマシン extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		plusCondition(ConditionEnum.ボーナスバリア);
		plusCondition(ConditionEnum.中の人);
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(29));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp % 3 == 0) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

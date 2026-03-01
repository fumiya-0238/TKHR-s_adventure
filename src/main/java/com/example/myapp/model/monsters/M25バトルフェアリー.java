package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M25バトルフェアリー extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(39));
		plusCondition(ConditionEnum.強攻撃無効);
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp % 2 == 0) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

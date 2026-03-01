package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M21ランタンオバケ extends Monster {
	
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(20));
		actions.add(CreateAction.INSTANCE.create(21));
		int[] status = { 1 };
		plusCondition(ConditionEnum.毎ターンHP回復, status);
	}
	
	@Override
	public void actions(Battle battle) {
		if(amountCondition(ConditionEnum.ランタンフレイム) == 1) {
			setAction(actions.get(2));
		} else if(hp%2 == 0) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}

}

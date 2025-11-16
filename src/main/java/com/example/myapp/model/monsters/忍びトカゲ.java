package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class 忍びトカゲ extends Monster {
	
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		plusCondition(ConditionEnum.先制攻撃);
		actions.add(CreateAction.INSTANCE.create(1));
	}
	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		setAction(actions.get(0));
	}

}

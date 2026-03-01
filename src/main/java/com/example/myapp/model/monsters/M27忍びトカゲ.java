package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M27忍びトカゲ extends Monster {
	
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		plusCondition(ConditionEnum.先制攻撃);
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(25));
		actions.add(CreateAction.INSTANCE.create(40));
	}
	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		//if()
		if(turn%3==0) {
			setAction(actions.get(1));
		}
		setAction(actions.get(0));
	}

}

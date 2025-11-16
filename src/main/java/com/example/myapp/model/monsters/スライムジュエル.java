package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class スライムジュエル extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(3));
		actions.add(CreateAction.INSTANCE.create(26));
		int[] status = { 0, -1, 2 };
		plusCondition(ConditionEnum.バリア,status);
	}

	@Override
	public void actions(Battle battle) {
		if(hp%3==0 && battle.getPlayer().amountCondition(ConditionEnum.スライム状態) == 0) {
			setAction(actions.get(2));
		} else if(turn == 0){
			setAction(actions.get(1));
		}else {
			setAction(actions.get(0));
		}
	}
}
package com.example.myapp.model.monsters;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class 寿命かじり虫 extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(24));
		int[] status = { 3 };
		plusCondition(ConditionEnum.毎ターンHP回復, status);
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (battle.getPlayer().getHP() <= getFinalAttack()) {
			setAction(actions.get(0));
		} else {
			setAction(actions.get(1));
		}
	}
}

package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M20ゴーレム extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(18));
		actions.add(CreateAction.INSTANCE.create(19));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp % 10 == 0) {
			setAction(actions.get(1));
		} else if (hp % 3 == 0) {
			setAction(actions.get(2));
		} else {
			setAction(actions.get(0));
		}
	}
}

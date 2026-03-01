package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M35紅葉ネペント extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(6));
		actions.add(CreateAction.INSTANCE.create(31));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (turn % 5 == 0) {
			setAction(actions.get(2));
		} else if (hp % 3 != 0) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

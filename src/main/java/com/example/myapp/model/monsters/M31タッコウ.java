package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M31タッコウ extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(28));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp % 3 == 0) {
			setAction(actions.get(1));
		} else if (hp % 4 == 0 && (hp * 100 / maxHp <= 50)) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

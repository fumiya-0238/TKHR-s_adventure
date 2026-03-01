package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M37コカトリス extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(32));
		actions.add(CreateAction.INSTANCE.create(33));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp * 100 / maxHp <= 50) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

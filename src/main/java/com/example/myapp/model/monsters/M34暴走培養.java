package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M34暴走培養 extends Monster {

	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(1));
		actions.add(CreateAction.INSTANCE.create(30));
	}

	@Override
	public void actions(Battle battle) {
		if (hp * 100 / maxHp < 20) {
			setAction(actions.get(1));
		} else if (getTurn() == 3 || getTurn() == 1) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

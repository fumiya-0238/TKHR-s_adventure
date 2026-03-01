package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M19ドッペルゲンガー extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		actions.add(CreateAction.INSTANCE.create(16));
		actions.add(CreateAction.INSTANCE.create(17));
	}

	@Override
	public void actions(Battle battle) {
		if (battle.getPlayer().descSearchItem("ダークマター") != -1) {
			setAction(actions.get(1));
		} else {
			setAction(actions.get(0));
		}
	}
}

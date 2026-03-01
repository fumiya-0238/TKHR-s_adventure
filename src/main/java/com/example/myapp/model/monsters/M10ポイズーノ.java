package com.example.myapp.model.monsters;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.repository.Battle;

public class M10ポイズーノ extends Monster {
	@Override
	public void reset() {
		// TODO 自動生成されたメソッド・スタブ
		int[] status = {2};
		actions.add(CreateAction.INSTANCE.create(7, status));
	}

	@Override
	public void actions(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ
		setAction(actions.get(0));	
	}
}

package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 火の玉 extends Item {
	public 火の玉() {
		super("火の玉", 12);
		setText("敵に3ダメージ与えます");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().calcDamageResult(battle, new ActionInfo(true, 3, true));
	}
}

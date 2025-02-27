package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 上級火の玉 extends Item {
	public 上級火の玉() {
		super("上級火の玉", 24);
		setText("敵に8ダメージ与えます");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().calcDamageResult(battle, new ActionInfo(true, 8, true));
	}
}
package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class 防御強化 extends Item {
	public 防御強化() {
		super("防御強化", 20);
		setText("このターン防御したときの回復量が2倍になります(重ねがけ可)");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().plusCondition(CreateCondition.DEFENCE_BUFF);
	}
}

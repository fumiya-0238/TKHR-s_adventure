package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class トゲトゲゾーン extends Item {
	public トゲトゲゾーン() {
		super("トゲトゲゾーン", 70);
		setText("毎ターン相手に5ダメージ");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO Auto-generated method stub
		battle.getMonster().plusCondition(CreateCondition.THORN);
	}

}

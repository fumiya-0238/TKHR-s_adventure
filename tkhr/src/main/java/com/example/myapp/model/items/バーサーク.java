package com.example.myapp.model.items;

import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.repository.Battle;

public class バーサーク extends Item {
	public バーサーク() {
		super("バーサーク", 20);
		setText("HPを10回復します");
		unique = true;
	}

	@Override
	public void use(Battle battle, int i) {
		battle.getPlayer().plusCondition(CreateCondition.BERSERK);
	}
}

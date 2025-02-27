package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ダークマター extends Item {
	public ダークマター() {
		super("ダークマター", 10);
		setText("20ダメージ受けます");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().calcDamageResult(battle, new ActionInfo(true, 20, true));
	}
}
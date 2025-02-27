package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ダメージ草 extends Item {
	public ダメージ草() {
		super("ダメージ草", 3);
		setText("使うと自分が10ダメージ受けます");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().calcDamageResult(battle, new ActionInfo(true, 10, true));
	}
}

package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 光の玉 extends Item {
	public 光の玉() {
		super("光の玉", 1);
		setText("敵に7ダメージ 強攻撃回数+1");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().setCritical(1);
		battle.getMonster().calcDamageResult(battle, new ActionInfo(true, 7, true));
	}
}

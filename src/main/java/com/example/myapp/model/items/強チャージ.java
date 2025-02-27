package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class 強チャージ extends Item {
	public 強チャージ() {
		super("強チャージ", 25);
		setText("強攻撃回数が1増えます");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().setCritical(1);
	}
}

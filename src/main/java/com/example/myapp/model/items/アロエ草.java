package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class アロエ草 extends Item {
	public アロエ草() {
		super("アロエ草", 30);
		setText("");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().healResult(15);
	}
}
package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class 気合ため extends Item {
	public 気合ため() {
		super("気合ため", 12);
		setText("気合を25%上げます");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().setTension(25);
	}
}
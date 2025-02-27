package com.example.myapp.model.items;

import com.example.myapp.repository.Battle;

public class タイムアッパー extends Item {
	public タイムアッパー() {
		super("タイムアッパー", 10);
		setText("ボーナスターンを1増やします");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().setTurn(1);
	}
}

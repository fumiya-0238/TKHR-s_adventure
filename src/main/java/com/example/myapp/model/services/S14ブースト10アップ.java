package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;

public class S14ブースト10アップ extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().plusBoost(10);
	}
}

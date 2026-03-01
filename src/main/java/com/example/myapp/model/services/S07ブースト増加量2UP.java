package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;

public class S07ブースト増加量2UP extends ServiceItem {
	@Override
	public void buy(Battle battle,int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().plusPlusBoost(2);
	}
}

package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;

public class S04最大HP5UP extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().setMAXHP(battle.getPlayer().getMAXHP() + 5);
	}
}

package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class S10武器融合 extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		if (battle.getPlayer().getWeapons().get(0).getId() == 1) {
			Battle.addLogs(new ScreenChange(ScreenEnum.お金が足りない, ""));
			return;
		}
		commonBuy(battle, price);
		battle.setFusionMode(true);
		battle.setFusionPrice(price);
		battle.getPlayer().plusPriceMulti(1.5);
		Battle.addLogs(new ScreenChange(ScreenEnum.融合画面, ""));
	}
}

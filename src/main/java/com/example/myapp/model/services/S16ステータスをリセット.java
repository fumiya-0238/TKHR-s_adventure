package com.example.myapp.model.services;

import com.example.myapp.repository.Battle;

public class S16ステータスをリセット extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().setLv(1);
		battle.playerReset();
		battle.getPlayer().setPriceMulti(0);
		battle.getPlayer().showItem();
	}

}

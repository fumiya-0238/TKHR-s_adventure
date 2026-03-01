package com.example.myapp.model.services;

import com.example.myapp.model.Player;
import com.example.myapp.repository.Battle;

public class HP全回復 extends ServiceItem {
	@Override
	public void buy(Battle battle,int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		Player player = battle.getPlayer();
		player.setHeal(player.getMAXHP());
	}
}

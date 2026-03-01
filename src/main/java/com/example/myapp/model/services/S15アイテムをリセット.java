package com.example.myapp.model.services;

import com.example.myapp.model.items.Item;
import com.example.myapp.repository.Battle;

public class S15アイテムをリセット extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		for (Item item : battle.getPlayer().getItems()) {
			battle.addRemoveItemList(item);
		}
		battle.clearItemList();
	}
}

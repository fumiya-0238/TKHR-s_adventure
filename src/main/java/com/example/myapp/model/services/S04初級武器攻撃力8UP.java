package com.example.myapp.model.services;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.Battle;

public class S04初級武器攻撃力8UP extends ServiceItem {
	@Override
	public void buy(Battle battle, int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().eternalCondition(ConditionEnum.初級武器強化);
	}
}

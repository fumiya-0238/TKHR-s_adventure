package com.example.myapp.model.services;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.Battle;

public class S08根性状態になる extends ServiceItem {
	@Override
	public void buy(Battle battle,int price) {
		// TODO 自動生成されたメソッド・スタブ
		commonBuy(battle, price);
		battle.getPlayer().plusCondition(ConditionEnum.根性);
	}
}
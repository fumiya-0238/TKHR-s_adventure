package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class デュランダル extends Weapon {
	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.貫通);
		player.setConditionTurn(ConditionEnum.貫通, "∞");
		player.plusCondition(ConditionEnum.強攻撃強化);
		player.plusMAXHP(20);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.貫通);
		player.removeCondition(ConditionEnum.強攻撃強化);
		player.plusMAXHP(-20);
	}
}

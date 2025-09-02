package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class ダイヤモンドの槍 extends Weapon{
	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.貫通);
		player.setConditionTurn(ConditionEnum.貫通,"∞");
		player.plusDefaultCritical(1);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.貫通);
		player.plusDefaultCritical(-1);
	}
}

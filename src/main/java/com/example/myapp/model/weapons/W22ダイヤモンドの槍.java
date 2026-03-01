package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W22ダイヤモンドの槍 extends Weapon{
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.貫通);
		player.plusDefaultCritical(1);
	}

	@Override
	public void takeOff(Player player) {
		player.minusCondition(ConditionEnum.貫通);
		player.plusDefaultCritical(-1);
	}
}

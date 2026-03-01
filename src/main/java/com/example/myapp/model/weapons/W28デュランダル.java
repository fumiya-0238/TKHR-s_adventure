package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W28デュランダル extends Weapon {
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.貫通);
		player.eternalCondition(ConditionEnum.強攻撃強化);
		player.plusMAXHP(20);
	}

	@Override
	public void takeOff(Player player) {
		player.minusCondition(ConditionEnum.貫通);
		player.minusCondition(ConditionEnum.強攻撃強化);
		player.plusMAXHP(-20);
	}
}

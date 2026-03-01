package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W07強強剣 extends Weapon {
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.強攻撃強化);
	}

	@Override
	public void takeOff(Player player) {
		player.minusCondition(ConditionEnum.強攻撃強化);
	}
}

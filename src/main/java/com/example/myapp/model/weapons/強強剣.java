package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class 強強剣 extends Weapon {
	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.強攻撃強化);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.強攻撃強化);
	}
}

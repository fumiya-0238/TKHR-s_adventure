package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W35天聖の剣 extends Weapon{
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.天聖の加護);
	}

	@Override
	public void takeOff(Player player) {
		player.minusCondition(ConditionEnum.天聖の加護);
		player.minusCondition(ConditionEnum.天聖の休息);
	}
}

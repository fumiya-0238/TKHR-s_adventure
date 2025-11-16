package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class VIPの剣 extends Weapon {
	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.VIP客);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.VIP客);
	}
}

package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W20ディレイソード extends Weapon {
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.ディレイモード);
	}
	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.ディレイモード);
	}
}

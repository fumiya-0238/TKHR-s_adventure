package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class キングスブレイド extends Weapon {
	@Override
	public void equip(Player player) {
		int[] status = { 0, -1, 1 };
		player.eternalCondition(ConditionEnum.カウンター, status);
		int[] status2 = { 9 };
		player.eternalCondition(ConditionEnum.毎ターンHP回復, status2);
	}

	@Override
	public void takeOff(Player player) {
		player.minusCondition(ConditionEnum.カウンター);
		player.minusCondition(ConditionEnum.毎ターンHP回復);
	}
}

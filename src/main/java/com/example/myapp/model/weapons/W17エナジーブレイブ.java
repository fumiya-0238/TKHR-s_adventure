package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W17エナジーブレイブ extends Weapon {
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.ヒールチャージ);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.ヒールチャージ);
	}
}

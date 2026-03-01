package com.example.myapp.model.weapons;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;

public class W14ブースティア extends Weapon {
	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.ブーストUP強化);
	}
	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.ブーストUP強化);
	}
}

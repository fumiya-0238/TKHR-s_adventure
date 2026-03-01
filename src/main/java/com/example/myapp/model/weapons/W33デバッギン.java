package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;

public class W33デバッギン extends Weapon {
	@Override
	public void equip(Player player) {
		player.plusMAXHP(200);
	}

	@Override
	public void takeOff(Player player) {
		player.plusMAXHP(-200);
	}
}

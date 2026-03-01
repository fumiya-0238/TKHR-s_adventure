package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;

public class W05骨の槍 extends Weapon{
	@Override
	public void equip(Player player) {
		player.plusDefaultCritical(1);
	}

	@Override
	public void takeOff(Player player) {
		player.plusDefaultCritical(-1);
	}
}
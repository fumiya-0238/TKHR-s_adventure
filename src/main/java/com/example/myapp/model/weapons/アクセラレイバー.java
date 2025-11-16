package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アクセラレイバー extends Weapon {
	@Override
	public void battleStart(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		int playerAttack = player.getAttack() - attack + battle.getFloor();
		attack = battle.getFloor();
		player.setAttack(playerAttack);
	}
}

package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 勇者の剣X extends Weapon {
	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.ためる強化);
		player.plusDefaultCritical(1);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.ためる強化);
		player.plusDefaultCritical(-1);
	}

	@Override
	public void turnEnd(Battle battle, List<ActionInfo> infos) {
		battle.getMonster().plusTurn(-1);
	}
}

package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W31勇者の剣X extends Weapon {
	private boolean brave;

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		brave = true;
		return super.attack(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		brave = true;
		return super.weekAttack(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		brave = true;
		return super.criticalAttack(battle, infos);
	}

	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.ためる強化);
		player.plusDefaultCritical(1);
	}

	@Override
	public void takeOff(Player player) {
		brave = false;
		player.minusCondition(ConditionEnum.ためる強化);
		player.plusDefaultCritical(-1);
	}

	@Override
	public void turnEnd(Battle battle, List<ActionInfo> infos) {
		if (brave == true) {
			battle.getTargetMonster().plusTurn(-1);
		}
		brave = false;
	}
}

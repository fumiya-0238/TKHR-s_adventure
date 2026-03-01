package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W06入魂剣 extends Weapon {
	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.根性);
		return super.criticalAttack(battle, infos);
	}
}
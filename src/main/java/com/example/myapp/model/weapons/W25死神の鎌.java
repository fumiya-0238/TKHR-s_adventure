package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W25死神の鎌 extends Weapon {
	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		battle.getTargetMonster().plusCondition(ConditionEnum.死神の呪い);
		return super.criticalAttack(battle, infos);
	}
}

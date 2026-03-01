package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class W34超炎の剣 extends Weapon {
	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.イグニッション);
		return super.criticalAttack(battle, infos);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.イグニッション);
	}
}

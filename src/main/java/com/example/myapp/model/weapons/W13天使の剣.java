package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class W13天使の剣 extends Weapon {
	private void angelHeal(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		ActionInfo info = new ActionInfo();
		info.setReceiver(player);
		info.setActionType(ActionTypeEnum.回復);
		info.setNumber(player.getMAXHP() / 10);
		infos.add(0,info);
	}

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {
		angelHeal(battle, infos);
		return super.attack(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		angelHeal(battle, infos);
		return super.weekAttack(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		angelHeal(battle, infos);
		return super.criticalAttack(battle, infos);
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos) {
		super.defence(battle, infos);
		angelHeal(battle, infos);
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos) {
		super.tension(battle, infos);
		angelHeal(battle, infos);
	}
}

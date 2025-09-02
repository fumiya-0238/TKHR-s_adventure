package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class チャージャーEX extends Weapon {
	@Override
	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		super.tension(battle, infos, n);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(4));
	}

	@Override
	public void equip(Player player) {
		player.plusCondition(ConditionEnum.ためる強化);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.ためる強化);
	}
}

package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I35クレイジーカード extends Item {

	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO Auto-generated method stub
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, battle.getPlayer(), 80, true, infos);
	}

	public void addItem(Player player) {
		player.setPriceMultiItem(0);
	}

	public void removeItem(Player player) {
		if (player.countItem(35) == 1) {
			player.setPriceMultiItem(1);
		}
	}
}

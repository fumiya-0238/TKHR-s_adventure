package com.example.myapp.model.items;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class I26気合サブスク extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		active = !active;
	}

	@Override
	public void turnStart(Battle battle, List<ActionInfo> infos) {
		if (!active) {
			return;
		}
		Player player = battle.getPlayer();
		int price = (int) (20 * player.getPriceMulti());
		if (price <= player.getGold()) {
			player.plusGold(-price);
			Battle.addLogs(new ScreenChange(ScreenEnum.買う, String.valueOf(price)));
			List<ActionInfo> infos2 = new ArrayList<>();
			ActionInfo info = new ActionInfo();
			info.setNumber(25);
			infos2.add(info);
		} else {
			battle.addRemoveItemList(this);
		}
	}
}

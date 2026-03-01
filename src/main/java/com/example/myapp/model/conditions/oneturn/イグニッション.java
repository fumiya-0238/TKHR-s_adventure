package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class イグニッション extends Condition{
	@Override
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		turn = 10;
		ActionInfo info = new ActionInfo();
		info.addMessages("状態発動:<" + name + ">", living);
		infos.add(info);
		Player player = battle.getPlayer();
		player.addItem(CreateItem.INSTANCE.create(15), player.getMaxItem() - player.getItems().size());
	}

	@Override
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			System.out.println(item.getId());
			if (item.getId() == 15) {
				battle.addRemoveItemList(item);
			}
		}
		battle.clearItemList();
	}
}

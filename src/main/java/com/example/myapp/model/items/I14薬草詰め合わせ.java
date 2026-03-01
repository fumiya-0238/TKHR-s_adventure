package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class I14薬草詰め合わせ extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> info) {
		// TODO 自動生成されたメソッド・スタブ
		Player player = battle.getPlayer();
		player.addItem(CreateItem.INSTANCE.create(1), player.getMaxItem() - player.getItems().size() + 1);
		// for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l;
		// j++) {
		// player.addItem(CreateItem.INSTANCE.create(1));
		// }
	}
}
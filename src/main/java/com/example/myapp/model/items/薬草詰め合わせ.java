package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 薬草詰め合わせ extends Item{
	@Override
	public void use(Battle battle, List<ActionInfo> info, int n) {
		// TODO 自動生成されたメソッド・スタブ
		Player player = battle.getPlayer();
		for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l; j++) {
			player.setItem(CreateItem.INSTANCE.create(1));
		}
	}

}
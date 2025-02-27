package com.example.myapp.model.items;

import com.example.myapp.model.Player;
import com.example.myapp.repository.Battle;

public class 薬草詰め合わせ extends Item {
	public 薬草詰め合わせ() {
		super("薬草詰め合わせ", 130);
		setText("空いてるアイテム欄を薬草で埋めます");
	}

	@Override
	protected void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		Player player = battle.getPlayer();
		for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l; j++) {
			player.setItem(new 薬草());
		}
	}
}

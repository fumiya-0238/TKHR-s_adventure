package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class タイムアッパー extends Item {

	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		ActionInfo info = new ActionInfo();
		if (0 < battle.getMonster().getTurn()) {
			battle.getMonster().plusTurn(3);
			info.addMessages("ボーナスターンが3増えた");
		} else {
			info.addMessages("効果がなかった");
		}
		infos.add(info);
	}
}

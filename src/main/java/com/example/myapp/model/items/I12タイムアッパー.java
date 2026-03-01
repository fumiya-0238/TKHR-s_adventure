package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class I12タイムアッパー extends Item {

	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		ActionInfo info = new ActionInfo();
		Monster monster = battle.getTargetMonster();
		if (0 < monster.getTurn()) {
			monster.plusTurn(3);
			info.addMessages("ボーナスターンが3増えた");
		} else {
			info.addMessages("効果がなかった");
		}
		infos.add(info);
	}
}

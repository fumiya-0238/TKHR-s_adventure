package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class とどめ玉 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		int hp = battle.getMonster().getHP();
		if (hp <= 5) {
			battle.getCommonEffect().commonDamege(battle, battle.getMonster(), battle.getMonster().getOverHP(), true,
					infos);

		} else {
			battle.getCommonEffect().commonDamege(battle, battle.getMonster(), 5, true, infos);
		}
	}
}
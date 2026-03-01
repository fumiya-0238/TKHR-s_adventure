package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I03とどめ玉 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		Monster monster = battle.getTargetMonster();
		int hp = monster.getHP();
		if (hp <= 5) {
			battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, monster, monster.getOverHP(), true, infos);

		} else {
			battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, monster, 5, true, infos);
		}
	}
}
package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 光の玉 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().plusCritical(1);
		battle.getCommonEffect().commonDamege(battle, battle.getMonster(), 7, true, infos);
	}
}

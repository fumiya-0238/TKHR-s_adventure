package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class とどめ玉 extends Item {

	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		int over = battle.getMonster().getOverHP();
		if (over <= 3) {
			infos.get(n).setDamage(over);// new ActionInfo(true, over, true)
			battle.getMonster().calcDamage(battle, infos, n);
		} else {
			infos.get(n).setDamage(5);
			battle.getMonster().calcDamage(battle, infos, n);
		}
	}
}
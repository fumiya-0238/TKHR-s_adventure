package com.example.myapp.model.items;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class とどめ玉 extends Item {
	public とどめ玉() {
		super("とどめ玉", 15);
		setText("敵に5ダメージ与えます 敵の残りHPが3以下なら敵をオーバーキルします");
	}

	@Override
	public void use(Battle battle, int i) {
		// TODO 自動生成されたメソッド・スタブ
		int over = battle.getMonster().getOverHP();
		if (over <= 3)
			battle.getMonster().calcDamageResult(battle, new ActionInfo(true, over, true));
		else
			battle.getMonster().calcDamageResult(battle, new ActionInfo(true, 5, true));
	}
}

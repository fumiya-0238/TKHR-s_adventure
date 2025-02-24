package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class とどめ玉 extends Item {
	public とどめ玉() {
		super("とどめ玉", 15);
		setText("敵に5ダメージ与えます 敵の残りHPが3以下なら敵をオーバーキルします");
	}

	@Override
	public String useResult(Battle battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		int over = battle.getMonster().getOverHP();
		if (over <= 3)
			return battle.getMonster().calcDamageResult(over, true, battle);
		else
			return battle.getMonster().calcDamageResult(5, true, battle);
	}
}

package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 火の玉 extends Item {
	public 火の玉() {
		super("火の玉", 12);
		setText("敵に3ダメージ与えます");
	}

	@Override
	public String useResult(Battle battle, int i,ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getMonster().calcDamageResult(3,true,battle);
	}
}

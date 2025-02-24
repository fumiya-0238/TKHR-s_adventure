package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 上級火の玉 extends Item {
	public 上級火の玉() {
		super("上級火の玉", 24);
		setText("敵に8ダメージ与えます");
	}

	@Override
	public String useResult(Battle battle, int i,ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getMonster().calcDamageResult(8,true,battle);
	}
}
package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 薬草 extends Item {
	public 薬草() {
		super("薬草", 10);
		setText("HPを10回復します");
	}

	@Override
	public String useResult(Battle battle, int i,ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getPlayer().healResult(10);
	}
}

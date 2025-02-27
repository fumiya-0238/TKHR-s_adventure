package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 気合ため extends Item {
	public 気合ため() {
		super("気合ため", 12);
		setText("空いてる持ち物欄を薬草で埋めます");
	}

	@Override
	public String useResult(GameService battle, int i,ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getPlayer().setTension(25);
	}
}
package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class アロエ草 extends Item {
	public アロエ草() {
		super("アロエ草", 30);
		setText("");
	}

	@Override
	protected String useResult(GameService battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getPlayer().healResult(15);
	}
}
package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 強チャージ extends Item{
	public 強チャージ() {
		super("強チャージ",25);
		setText("強攻撃回数が1増えます");
	}


	@Override
	protected String useResult(GameService battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().setCritical(1);
		itemInterface.removeItem(i);
		return battle.getPlayer().setCritical(1);
	}
}

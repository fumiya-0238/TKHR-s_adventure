package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class ダメージ草 extends Item {
	public ダメージ草() {
		super("ダメージ草", 3);
		setText("使うと自分が10ダメージ受けます");
	}

	@Override
	protected String useResult(Battle battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		return battle.getPlayer().calcDamageResult(10, true, battle);
	}
}

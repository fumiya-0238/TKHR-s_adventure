package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 防御強化 extends SystemItem {
	public 防御強化() {
		super("防御強化", 20);
		setText("このターン防御したときの回復量が2倍になります(重ねがけ可)");
	}

	@Override
	protected String useResult(Battle battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		super.registration(battle, i, itemInterface);
		lastTurn = 1;
		return "";
	}
}

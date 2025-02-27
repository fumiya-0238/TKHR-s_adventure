package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class タイムアッパー extends Item {
	public タイムアッパー() {
		super("タイムアッパー", 10);
		setText("HPを10回復します");
	}

	@Override
	public String useResult(GameService battle, int i,ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		itemInterface.removeItem(i);
		battle.getMonster().setTurn(1);
		return "";
	}
}

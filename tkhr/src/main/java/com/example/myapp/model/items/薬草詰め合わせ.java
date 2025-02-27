package tkhr.items;

import tkhr.Battle;
import tkhr.Player;
import tkhr.battleScreen.itemPanel.ItemInterface;

public class 薬草詰め合わせ extends Item {
	public 薬草詰め合わせ() {
		super("薬草詰め合わせ", 130);
		setText("空いてるアイテム欄を薬草で埋めます");
	}

	@Override
	protected String useResult(GameService battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		Player player = battle.getPlayer();
		for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l; j++)
			battle.setItem(new 薬草());
		itemInterface.removeItem(i);
		return "薬草を大量に手に入れた";
	}
}

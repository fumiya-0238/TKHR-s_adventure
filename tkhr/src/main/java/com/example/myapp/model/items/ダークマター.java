package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;
import tkhr.battleScreen.messagePanel.MessagePanel;

public class ダークマター extends Item {
	public ダークマター() {
		super("ダークマター", 10);
		setText("20ダメージ受ける");
	}

	@Override
	public void use(Battle battle, int i, MessagePanel message, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		message.noWaitMessage(getName() + "を使った！");
		battle.getPlayer().calcDamage(20,true);
		itemInterface.removeItem(i);
	}
}
package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;
import tkhr.battleScreen.messagePanel.MessagePanel;

public class 気合サブスク extends SubscriptionItem implements ItemObserver {
	public 気合サブスク() {
		super("気合サブスク", 40);
		setText("毎ターン40G消費し、気合を25%上げます。");
	}

	@Override
	public void turnStart(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void turnEnd(Battle battle) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void use(Battle battle, int i, MessagePanel message, ItemInterface iface) {
		// TODO 自動生成されたメソッド・スタブ
		super.use(battle, i, iface, this);
		message.noWaitMessage(getName() + "を使った！");
	}

	@Override
	protected String useResult(Battle battle, int i, ItemInterface itemInterface) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}

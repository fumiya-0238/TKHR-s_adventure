package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public abstract class SystemItem extends Item {
	protected int lastTurn;

	public SystemItem(String name, int price) {
		super(name, price);
	}

	public int getTurn() {
		return lastTurn;
	}

	public void setTurn(int turn) {
		lastTurn += turn;
	}

	protected void registration(GameService battle, int i, ItemInterface itemInterface) {
		battle.addSystemItem(this);
		itemInterface.removeItem(i);
	}
}

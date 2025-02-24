package tkhr.items;

import tkhr.Battle;
import tkhr.battleScreen.itemPanel.ItemInterface;

public abstract class Item {
	private String name;
	private int price;
	private String text;
	protected boolean unique;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	protected abstract String useResult(Battle battle, int i, ItemInterface itemInterface);

	public String commonUse(Battle battle, int i, ItemInterface itemInterface) {
		StringBuilder sb = new StringBuilder(name);
		sb.append("を使った。");
		sb.append(useResult(battle, i, itemInterface));
		return sb.toString();
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

package com.example.myapp.repository.debug;

public class ShopItemData extends TKHRData {
	private int itemId;
	private int stock;

	public ShopItemData(int id, int itemId, int stock) {
		super(id);
		this.itemId = itemId;
		this.stock = stock;
	}

	public int getItemId() {
		return itemId;
	}

	public int getStock() {
		return stock;
	}
}

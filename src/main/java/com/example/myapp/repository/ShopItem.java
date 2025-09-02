package com.example.myapp.repository;

public class ShopItem {
	private int shopId;
	private int id;
	private String name;
	private String furigana;
	private int price;
	private String stock;

	public ShopItem(int shopId, int id, String name, String furigana, int price, String stock) {
		this.shopId = shopId;
		this.id = id;
		this.name = name;
		this.furigana = furigana;
		this.price = price;
		this.stock = stock;
	}

	public int getShopId() {
		return shopId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFurigana() {
		return furigana;
	}

	public int getPrice() {
		return price;
	}

	public String getStock() {
		return stock;
	}

	public boolean plusStock(int stock) {
		if (this.stock.equals("∞")) {
			return false;
		} else {
			int s = Integer.parseInt(this.stock) + stock;
			this.stock = String.valueOf(s);
		}
		return (this.stock.equals("0"));
	}
}

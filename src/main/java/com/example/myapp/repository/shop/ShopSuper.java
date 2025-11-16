package com.example.myapp.repository.shop;

public class ShopSuper {
	private int shopId;
	private int id;
	private String name;
	private String furigana;
	private int price;
	private int stock;

	public ShopSuper(int shopId, int id, String name, String furigana, int price, int stock) {
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

	public int getStock() {
		return stock;
	}

	public boolean plusStock(int stock) {
		if (this.stock == 0) {
			return false;
		}
		this.stock += stock;
		return this.stock == 0;
	}
}
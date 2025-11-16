package com.example.myapp.repository.shop;

public class ShopWeapon extends ShopSuper {
	private int attack;

	public ShopWeapon(int shopId, int id, String name, int attack, String furigana, int price, int stock) {
		super(shopId, id, name, furigana, price, stock);
		this.attack = attack;
	}

	public int getAttack() {
		return attack;
	}
}

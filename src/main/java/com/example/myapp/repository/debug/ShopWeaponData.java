package com.example.myapp.repository.debug;

public class ShopWeaponData extends TKHRData {
	private int weaponId;
	private int stock;

	public ShopWeaponData(int id, int weaponId, int stock) {
		super(id);
		this.weaponId = weaponId;
		this.stock = stock;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public int getStock() {
		return stock;
	}
}

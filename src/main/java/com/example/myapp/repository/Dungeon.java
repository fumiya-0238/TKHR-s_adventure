package com.example.myapp.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.myapp.repository.shop.ShopItem;
import com.example.myapp.repository.shop.ShopService;
import com.example.myapp.repository.shop.ShopWeapon;

public class Dungeon {
	private List<Integer> monsterId;
	private List<Integer> monsterLv;
	private List<ShopItem> items;
	private List<ShopWeapon> weapons;
	private List<ShopService> services;
	private List<Integer> shopFloors;

	public void setStatus(List<Integer> monsterId,List<Integer> monsterLv, List<ShopWeapon> weapons, List<ShopItem> items,
			List<ShopService> services, List<Integer> shopFloors) {
		this.monsterId = monsterId;
		this.monsterLv = monsterLv;
		this.weapons = weapons;
		this.items = items;
		this.services = services;
		this.shopFloors = shopFloors;
	}

	public List<Integer> getMonsters() {
		return monsterId;
	}
	
	public List<Integer> getMonsterLv() {
		return monsterLv;
	}
	public List<ShopWeapon> getWeapons() {
		return weapons;
	}

	public List<ShopItem> getItems() {
		return items;
	}

	public List<ShopService> getServices() {
		return services;
	}

	public int getShopFloor() {
		if(shopFloors.size() == 0) {
			return 0;
		}
		return shopFloors.get(0);
	}

	public void floorRemove() {
		shopFloors.remove(0);
	}

	public void sortItem(String sort) {
		switch (sort) {
		case "種類順":
			Collections.sort(items, Comparator.comparing(ShopItem::getShopId));
			break;
		case "値段順":
			Collections.sort(items, Comparator.comparing(ShopItem::getPrice));
			break;
		case "名前順":
			Collections.sort(items, Comparator.comparing(ShopItem::getFurigana));
			break;
		}
	}

	public void sortWeapon(String sort) {
		switch (sort) {
		case "種類順":
			Collections.sort(weapons, Comparator.comparing(ShopWeapon::getShopId));
			break;
		case "値段順":
			Collections.sort(weapons, Comparator.comparing(ShopWeapon::getPrice));
			break;
		case "名前順":
			Collections.sort(weapons, Comparator.comparing(ShopWeapon::getFurigana));
			break;
		case "攻撃力順":
			Collections.sort(weapons, Comparator.comparing(ShopWeapon::getAttack));
			break;
		}
	}
}

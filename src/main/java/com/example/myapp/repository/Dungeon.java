package com.example.myapp.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dungeon {
	private List<Integer> monsters;
	private List<ShopItem> items;
	private List<ShopWeapon> weapons;

	public void setStatus(List<Integer> monsters, List<ShopWeapon> weapons, List<ShopItem> items) {
		this.monsters = monsters;
		this.weapons = weapons;
		this.items = items;
	}

	public List<Integer> getMonsters() {
		return monsters;
	}

	public List<ShopWeapon> getWeapons() {
		return weapons;
	}

	public List<ShopItem> getItems() {
		return items;
	}
	
	public void sortItem(String sort) {
		switch(sort) {
		case "種類順":
			Collections.sort(items, Comparator.comparing(ShopItem::getId));
			break;
		case "値段順":
			Collections.sort(items, Comparator.comparing(ShopItem::getPrice));
			break;
		case "名前順":
			Collections.sort(items, Comparator.comparing(ShopItem::getFurigana));
			break;
		}
		
	}

}

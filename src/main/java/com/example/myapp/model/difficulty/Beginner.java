package com.example.myapp.model.difficulty;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.とどめ玉;
import com.example.myapp.model.items.ダメージ草;
import com.example.myapp.model.items.バーサーク;
import com.example.myapp.model.items.強チャージ;
import com.example.myapp.model.items.火の玉;
import com.example.myapp.model.items.挑発;
import com.example.myapp.model.items.薬草;
import com.example.myapp.model.items.防御強化;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.weapons.入魂剣;
import com.example.myapp.model.weapons.強強剣;
import com.example.myapp.model.weapons.打製石器;
import com.example.myapp.model.weapons.木の枝;
import com.example.myapp.model.weapons.石の剣;
import com.example.myapp.model.weapons.鉄の剣;
import com.example.myapp.model.weapons.鉄の槍;
import com.example.myapp.model.weapons.骨の槍;

public class Beginner extends Difficulty {
	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public Monster getMonster(int floor) {
		if (floor == 1)
			return cm.create(1);
		if (floor == 2 || floor == 3)
			return cm.create(2);
		if (floor == 4 || floor == 5)
			return cm.create(3);
		if (floor == 6 || floor == 7)
			return cm.create(4);
		if (floor == 8 || floor == 9)
			return cm.create(5);
		return cm.create(6);
	}

	@Override
	public List<Weapon> getWeapons() {
		List<Weapon> weapon = new ArrayList<Weapon>();
		weapon.add(new 木の枝());
		weapon.add(new 打製石器());
		weapon.add(new 石の剣());
		weapon.add(new 鉄の剣());
		weapon.add(new 骨の槍());
		weapon.add(new 鉄の槍());
		weapon.add(new 強強剣());
		weapon.add(new 入魂剣());
		return weapon;
	}

	@Override
	public List<Item> getItems() {
		List<Item> item = new ArrayList<Item>();
		item.add(new 薬草());
		item.add(new 火の玉());
		item.add(new とどめ玉());
		item.add(new 強チャージ());
		item.add(new 防御強化());
		item.add(new 挑発());
		item.add(new ダメージ草());
		item.add(new バーサーク());
		return item;
	}
}

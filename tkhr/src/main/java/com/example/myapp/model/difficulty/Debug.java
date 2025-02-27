package com.example.myapp.model.difficulty;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.とどめ玉;
import com.example.myapp.model.items.ダメージ草;
import com.example.myapp.model.items.バーサーク;
import com.example.myapp.model.items.強チャージ;
import com.example.myapp.model.items.火の玉;
import com.example.myapp.model.items.薬草;
import com.example.myapp.model.items.防御強化;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.weapons.ジェネシスリンク;
import com.example.myapp.model.weapons.デバッギン;

public class Debug extends Expert {
    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public Monster getMonster(int floor) {
        // TODO 自動生成されたメソッド・スタブ
        return cm.create(1);
    }

    @Override
    public List<Weapon> getWeapons() {
        // TODO 自動生成されたメソッド・スタブ
        List<Weapon> weapon = new ArrayList<Weapon>();
        weapon.addAll(super.getWeapons());
        weapon.add(new ジェネシスリンク());
        weapon.add(new デバッギン());
        return weapon;
    }

    @Override
    public List<Item> getItems() {
        // TODO 自動生成されたメソッド・スタブ
        List<Item> item = new ArrayList<Item>();
        item.add(new 薬草());
        item.add(new 火の玉());
        item.add(new とどめ玉());
        item.add(new 強チャージ());
        item.add(new 防御強化());
        item.add(new ダメージ草());
        item.add(new バーサーク());
        return item;
    }
}
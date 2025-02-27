package com.example.myapp.model.difficulty;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.アロエ草;
import com.example.myapp.model.items.ゴールドチャレンジ;
import com.example.myapp.model.items.ブーストアップ;
import com.example.myapp.model.items.上級火の玉;
import com.example.myapp.model.items.気合ため;
import com.example.myapp.model.items.研ぎ石;
import com.example.myapp.model.items.薬草詰め合わせ;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.weapons.VIPの剣;
import com.example.myapp.model.weapons.チャージャーEX;
import com.example.myapp.model.weapons.ディレイソード;
import com.example.myapp.model.weapons.ブースティア;
import com.example.myapp.model.weapons.ライトブリンガー;
import com.example.myapp.model.weapons.天使の剣;
import com.example.myapp.model.weapons.火炎の剣;
import com.example.myapp.model.weapons.貫通剣;

public class Novice extends Beginner {
    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public Monster getMonster(int floor) {
        if (floor == 1 || floor == 2)
            return cm.create(7);
        if (floor == 3 || floor == 9 || floor == 18)
            return cm.create(8);
        if (floor == 4 || floor == 5)
            return cm.create(9);
        if (floor == 6)
            return cm.create(10);
        if (floor == 7 || floor == 17)
            return cm.create(11);
        if (floor == 8)
            return cm.create(12);
        if (floor == 10 || floor == 13)
            return cm.create(13);
        if (floor == 11)
            return cm.create(14);
        if (floor == 12)
            return cm.create(15);
        if (floor == 14)
            return cm.create(16);
        if (floor == 15)
            return cm.create(17);
        if (floor == 16)
            return cm.create(18);
        if (floor == 19)
            return cm.create(19);
        return cm.create(20);
    }

    @Override
    public List<Weapon> getWeapons() {
        List<Weapon> weapon = new ArrayList<Weapon>();
        weapon.addAll(super.getWeapons());
        weapon.add(new 貫通剣());
        // weapon.add(new ぼろ双剣());
        weapon.add(new 天使の剣());
        weapon.add(new 火炎の剣());
        weapon.add(new ブースティア());
        weapon.add(new チャージャーEX());
        weapon.add(new VIPの剣());
        weapon.add(new ディレイソード());
        weapon.add(new ライトブリンガー());
        return weapon;
    }

    @Override
    public List<Item> getItems() {
        List<Item> item = new ArrayList<Item>();
        item.addAll(super.getItems());
        item.add(new アロエ草());
        item.add(new 薬草詰め合わせ());
        item.add(new 上級火の玉());
        item.add(new 気合ため());
        item.add(new 研ぎ石());
        item.add(new ブーストアップ());
        item.add(new ゴールドチャレンジ());
        return item;
    }
}

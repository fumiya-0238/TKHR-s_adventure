package com.example.myapp.model.difficulty;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.バリア;
import com.example.myapp.model.items.タイムアッパー;
import com.example.myapp.model.items.気合サブスク;
import com.example.myapp.model.items.トゲトゲゾーン;
import com.example.myapp.model.items.初級武器強化セット;
import com.example.myapp.model.items.伝説のハンマー;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.weapons.ダイヤモンドの槍;
import com.example.myapp.model.weapons.タイタンブレード;
import com.example.myapp.model.weapons.世界樹の剣;
import com.example.myapp.model.weapons.死神の鎌;
import com.example.myapp.model.weapons.デュランダル;
import com.example.myapp.model.weapons.エクスカリバー;
import com.example.myapp.model.weapons.SIMPLE_IS_BEST;
import com.example.myapp.model.weapons.デザイアの剣;
import com.example.myapp.model.weapons.勇者の剣X;

public class Expert extends Novice {

    @Override
    public int getLevel() {
        return 10;
    }

    @Override
    public Monster getMonster(int floor) {
        if (1 <= floor && floor <= 16)
            return cm.create(floor + 20);
        if (floor == 17)
            return cm.create(28);
        if (18 <= floor && floor <= 19)
            return cm.create(floor + 19);
        return cm.create(39);
    }

    @Override
    public List<Weapon> getWeapons() {
        List<Weapon> weapon = new ArrayList<Weapon>();
        weapon.addAll(super.getWeapons());
        weapon.add(new ダイヤモンドの槍());
        weapon.add(new タイタンブレード());
        weapon.add(new 世界樹の剣());
        weapon.add(new 死神の鎌());
        weapon.add(new デュランダル());
        weapon.add(new エクスカリバー());
        weapon.add(new SIMPLE_IS_BEST());
        weapon.add(new デザイアの剣());
        weapon.add(new 勇者の剣X());
        return weapon;
    }

    @Override
    public List<Item> getItems() {
        List<Item> item = new ArrayList<Item>();
        item.add(new バリア());
        item.add(new タイムアッパー());
        item.add(new 気合サブスク());
        item.add(new トゲトゲゾーン());
        item.add(new 初級武器強化セット());
        item.add(new 伝説のハンマー());
        return item;
    }
}
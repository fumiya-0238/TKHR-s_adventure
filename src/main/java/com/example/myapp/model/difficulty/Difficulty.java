package com.example.myapp.model.difficulty;

import java.util.List;

import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.CreateMonster;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;

public abstract class Difficulty {
    protected CreateMonster cm = CreateMonster.INSTANCE;

    public abstract int getLevel();

    public abstract Monster getMonster(int floor);

    public abstract List<Weapon> getWeapons();

    public abstract List<Item> getItems();
}

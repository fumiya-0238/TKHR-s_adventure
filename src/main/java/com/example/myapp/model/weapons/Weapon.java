package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Weapon {
	protected int id;
	protected String name;
	protected int price;
	protected int attack;

	public void setStatus(int id, String name, int price, int attack) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.attack = attack;
	}

	public void equip(Player player) {
	}

	public void takeOff(Player player) {

	}

	public void battleStart(Battle battle, List<ActionInfo> infos) {
	}

	public void turnStart(Battle battle, List<ActionInfo> infos) {
	}

	public void turnEnd(Battle battle, List<ActionInfo> infos) {
	}

	public int attack(Battle battle, List<ActionInfo> infos) {
		return 0;
	}

	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		return 0;
	}

	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		return 0;
	}

	public void defence(Battle battle, List<ActionInfo> infos) {
	}

	public void tension(Battle battle, List<ActionInfo> infos) {
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
}
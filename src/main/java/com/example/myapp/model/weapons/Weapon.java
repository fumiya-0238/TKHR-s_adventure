package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
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

	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		if (battle.getPlayer().getWeapons().get(0).getId() == id) {
			Player player = battle.getPlayer();
			infos.get(n).setAttackIs(true);
			double tension = 1.0 + ((double) player.getTension() / 100);
			infos.get(n).setDamage((int) (player.getAttack() * tension));
			infos.get(n).setPenetrate(player.amountCondition(ConditionEnum.貫通) > 0);
			player.resetTension();
		}
	}

	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		if (battle.getPlayer().getWeapons().get(0).getId() == id) {
			Player player = battle.getPlayer();
			infos.get(n).setAttackIs(true);
			infos.get(n).setDamage(player.getAttack() - 1);
			infos.get(n).setPenetrate(player.amountCondition(ConditionEnum.貫通) > 0);
		}
	}

	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		if (battle.getPlayer().getWeapons().get(0).getId() == id) {
			Player player = battle.getPlayer();
			player.plusCritical(-1);
			infos.get(n).setAttackIs(true);
			double tension = 1.0 + ((double) player.getTension() / 100);
			infos.get(n).setDamage((int) (player.getAttack() * tension * 1.5));
			infos.get(n).setPenetrate(player.amountCondition(ConditionEnum.貫通) > 0);
			player.resetTension();
		}
	}

	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		if (battle.getPlayer().getWeapons().get(0).getId() == id) {
			Player player = battle.getPlayer();
			player.plusCondition(battle, infos, n, ConditionEnum.防御);
		}
	}

	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		if (battle.getPlayer().getWeapons().get(0).getId() == id) {
			battle.getPlayer().plusTension(battle, infos, n, 25);
		}
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
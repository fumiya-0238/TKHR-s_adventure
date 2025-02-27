package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.model.difficulty.Difficulty;
import com.example.myapp.model.monsters.Monster;

public class Battle {
	private Player player;
	private Monster monster;
	private List<String> messages;
	private int playerDamege;
	private int playerTension;
	private int monsterDamage;

	public Battle() {
		player = new Player();
		messages = new ArrayList<String>();
	}

	public void playerReset() {
		player.resetStatus();
	}

	public void setPlayerLV(Difficulty difficulty) {
		player.setLV(difficulty.getLevel());
	}

	public void playerAttack() {
		ActionInfo info = new ActionInfo();
		player.attack(this, info);
		// monster.calcDamage(this);

		// monster.actions(this);
		// player.turnEnd();
		// monster.turnEnd();
	}

	/*
	 * public void setPlayerWeapon() {
	 * player.setLV(difficulty.getLevel());
	 * }
	 * 
	 * public void setPlayerItem(Difficulty difficulty) {
	 * player.setLV(difficulty.getLevel());
	 * }
	 */
	public void monsterTurn() {
		ActionInfo info = new ActionInfo();
		monster.actions(this, info);

	}

	public void setPlayerCondition() {

	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public Player getPlayer() {
		return player;
	}

	public Monster getMonster() {
		return monster;
	}
}

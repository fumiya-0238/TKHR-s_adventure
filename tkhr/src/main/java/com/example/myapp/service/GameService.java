package com.example.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myapp.model.difficulty.Difficulty;
import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ImagesRepository;

@Service
public class GameService {
	private Battle battle;
	private Difficulty difficulty;
	private int sumTurn;
	private int floor;

	public GameService() {
		battle = new Battle();
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void goFirstFloor() {
		floor = 1;
		sumTurn = 1;
		battle.playerReset();
		battle.getPlayer().setLV(difficulty.getLevel());
		battle.setMonster(difficulty.getMonster(floor));
	}

	public void nextFloor() {
		floor++;
		battle.setMonster(difficulty.getMonster(floor));
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void playerAttack() {
		battle.playerAttack();
	}

	public void playerWeekAttack() {
		battle.playerAttack();
	}

	public void playerCriticalAttack() {
		battle.playerAttack();
	}

	public void playerDefence() {
		// player.defence();
	}

	public void PlayerTension() {
	}

	/*
	 * public void end() {
	 * 
	 * // player.setEXPGold(monster.getEXP(), floor);
	 * }
	 * 
	 * public void victory() {
	 * // int EXP=monster.getEXP()
	 * 
	 * // player.setEXPGold(monster.getEXP(), monster.getGold(), monster.getTurn() >
	 * 0,
	 * // monster.getOverHP() == 0);
	 * }
	 */
	public String getMonsterImage() {
		return ImagesRepository.INSTANCE.getMonsterImage(battle.getMonster().getID(), 205);
	}

	public String makeJSON() {
		StringBuilder column = new StringBuilder();
		column.append("{");
		makePlayerJSON(column);
		makeMonsterJSON(column);
		column.append("\"Floor\":\"" + floor + "\"");
		column.append("}");
		return column.toString();
	}

	public void makePlayerJSON(StringBuilder column) {
		Player player = battle.getPlayer();
		column.append("\"Player\":{");
		column.append("\"LV\":\"" + player.getLV() + "\",");
		column.append("\"HP\":\"" + player.getHP() + "\",");
		column.append("\"MAXHP\":\"" + player.getMAXHP() + "\",");
		column.append("\"ATK\":\"" + player.getATK() + "\",");
		makeWeaponJSON(column);
		column.append("\"Tension\":\"" + player.getTension() + "\",");
		column.append("\"EXP\":\"" + player.getEXP() + "\",");
		column.append("\"Gold\":\"" + player.getGold() + "\",");
		column.append("\"Critical\":\"" + player.getCritical() + "\",");
		makeItemsJSON(column);
		column.append("},");

	}

	public void makeWeaponJSON(StringBuilder column) {
		Weapon weapon = battle.getPlayer().getWeapon();
		column.append("\"Weapon\":{");
		column.append("\"Name\":\"" + weapon.getName() + "\",");
		column.append("\"ATK\":\"" + weapon.getATK() + "\",");
		column.append("\"Text\":\"" + weapon.getText() + "\"");
		column.append("},");
	}

	public void makeItemsJSON(StringBuilder column) {
		List<Item> items = battle.getPlayer().getItems();
		column.append("\"Items\":[");
		for (int i = 0, l = items.size(); i < l; i++) {
			Item item = items.get(i);
			column.append("{");
			column.append("\"Name\":\"" + item.getName() + "\",");
			column.append("\"Text\":\"" + item.getText() + "\"");
			column.append("}");
			if (i < l - 1)
				column.append(",");
		}
		column.append("]");
	}

	public void makeMonsterJSON(StringBuilder column) {
		Monster monster = battle.getMonster();
		column.append("\"Monster\":{");
		column.append("\"Name\":\"" + monster.getName() + "\",");
		column.append("\"HP\":\"" + monster.getHP() + "\",");
		column.append("\"OverHP\":\"" + monster.getOverHP() + "\",");
		column.append("\"ATK\":\"" + monster.getATK() + "\",");
		column.append("\"EXP\":\"" + monster.getEXP() + "\",");
		column.append("\"Gold\":\"" + monster.getGold() + "\",");
		column.append("\"Turn\":\"" + monster.getTurn() + "\"");
		column.append("},");
	}
}
package com.example.myapp.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Difficulty;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.SubscriptionItem;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.book.PictureBook;

public class Battle {
	private Player player;
	private Monster monster;
	private int playerDamage;
	private int playerTension;
	private int monsterDamage;
	private PictureBook pictureBook;
	private List<String> messages;
	private int turn;
	private static List<ScreenChange> logs;
	private Difficulty difficulty;
	private int floor;
	private Dungeon dungeon;

	public Battle() {
		player = new Player();
		messages = new ArrayList<>();
		logs = new ArrayList<>();
	}

	public void playerReset() {
		player.resetStatus();
	}

	public void setPlayerLV(Difficulty difficulty) {
		player.setLV(difficulty.getLv());
	}

	public void battleStart() {
		turn = 1;
		messages.clear();
		List<ActionInfo> infos = new ArrayList<>();
		for (Weapon weapon : player.getWeapons()) {
			weapon.battleStart(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.battleStart(this, player, infos);
		}
		for (Condition condition : monster.getConditions()) {
			condition.battleStart(this, monster, infos);
		}
		player.resetCritical();
		for (ActionInfo info : infos) {
			messages.addAll(info.getMessages());
		}
		turnStart();
	}

	public void turnStart() {
		List<ActionInfo> infos = new ArrayList<>();
		ActionInfo turnInfo = new ActionInfo();
		turnInfo.addMessages(turn + "ターン目");
		infos.add(turnInfo);
		for (Weapon weapon : player.getWeapons()) {
			weapon.turnStart(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.turnStart(this, player, infos);
		}
		for (Condition condition : monster.getConditions()) {
			condition.turnStart(this, monster, infos);
		}
		for (ActionInfo info : infos) {
			messages.addAll(info.getMessages());
		}
	}

	public void turnEnd(List<ActionInfo> infos) {
		for (Weapon weapon : player.getWeapons()) {
			weapon.turnEnd(this, infos);
		}
		for (Condition condition : player.getConditions()) {
			condition.turnEnd(this, player, infos);
		}
		for (Condition condition : monster.getConditions()) {
			condition.turnEnd(this, monster, infos);
		}
		monster.plusTurn(-1);
	}

	public void playerTurn(int action) {
		List<ActionInfo> infos = new ArrayList<>();
		//infos.add(new ActionInfo());
		//ActionInfo info = new ActionInfo();
		player.setAction(action);
		switch (action) {
		case 1:
			player.attack(this, infos, 0);
			break;
		case 2:
			player.weekAttack(this, infos, 0);
			break;
		case 3:
			player.criticalAttack(this, infos, 0);
			break;
		case 4:
			player.defence(this, infos, 0);
			break;
		case 5:
			player.tension(this, infos, 0);
			break;
		}
		player.plusBoost(2);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ, ""));
		if (0 < monster.getHP()) {
			monsterTurn(infos);
		}
		turnEnd(infos);
		for (ActionInfo info : infos) {
			messages.addAll(info.getMessages());
		}
		player.setAction(0);
		if (0 < monster.getHP()) {
			turnStart();
		} else {
			player.setEXPGold(monster.getEXP(), monster.getGold(), turn > 0, monster.getOverHP() == 0);
			if (floor == dungeon.getMonsters().size()) {
				Battle.addLogs(new ScreenChange(ScreenEnum.クリア, ""));
			}
			if (floor % 5 == 0) {
				Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			} else if (difficulty == Difficulty.BEGINNER && floor == 9) {
				Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			} else if (difficulty == Difficulty.NOVICE && floor == 19) {
				Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			} else if (difficulty == Difficulty.EXPERT && floor == 19) {
				Battle.addLogs(new ScreenChange(ScreenEnum.店, ""));
			} else {
				Battle.addLogs(new ScreenChange(ScreenEnum.次の階, ""));
			}
		}
	}

	/*
		public void playerDefence() {
			List<ActionInfo> infos = new ArrayList<>();
			infos.add(new ActionInfo());
			player.defence(this, infos, 0);
		}
	*/
	public void useItem(int i) {
		List<ActionInfo> infos = new ArrayList<>();
		//infos.add(new ActionInfo());
		Item item = player.getItems().get(i);
		if (!(item instanceof SubscriptionItem && !item.getActive())) {
			player.removeItem(this, i);
		}
		item.use(this, infos, 0);
		for (Condition condition : player.getConditions()) {
			condition.useItem(this, player, infos, 0);
		}
	}

	public void monsterTurn(List<ActionInfo> infos) {
		//infos.add(new ActionInfo());
		monster.actions(this);
		for (Condition condition : monster.getConditions()) {
			condition.monsterAction(this);
		}
		monster.getAction().doAction(this, infos, 0);
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

	public PictureBook getPictureBook() {
		return pictureBook;
	}

	public int getMonsterDamage() {
		return monsterDamage;
	}

	public void setPictureBook(PictureBook pictureBook) {
		this.pictureBook = pictureBook;
	}

	public List<String> getMessages() {
		return messages;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public int getFloor() {
		return floor;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
		Battle.addLogs(new ScreenChange(ScreenEnum.難易度, difficulty.getName()));
	}

	public void setFloor(int floor) {
		this.floor = floor;
		Battle.addLogs(new ScreenChange(ScreenEnum.階数, String.valueOf(floor)));
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public static void addLogs(ScreenChange sc) {
		logs.add(sc);
	}

	public static List<ScreenChange> getLogs() {
		return logs;
	}

	public static void clearLogs() {
		logs.clear();
	}
}
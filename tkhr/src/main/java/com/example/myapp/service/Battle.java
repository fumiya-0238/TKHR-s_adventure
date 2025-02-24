package tkhr;

import java.util.ArrayList;
import java.util.List;

import tkhr.difficulty.Difficulty;
import tkhr.monsters.Monster;

public class Battle implements BattleSubject {
	private Difficulty d;
	private Player player;
	private Monster monster;
	private int sumTurn;
	private int floor;
	private List<Observer> observers;
	private int playerDamege;
	private int playerTension;
	private int monsterDamage;

	public Battle() {
		player = new Player();
		observers = new ArrayList<Observer>();

	}

	public void setDifficulty(Difficulty difficulty) {
		d = difficulty;
	}

	public void goFirstFloor() {
		player.resetStatus();
		player.setLV(d.getLevel());
		setFloor(1);
		sumTurn = 1;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void encountMonster() {
		monster = d.getMonster(floor);
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public void turn() {
		//messagePanel.startMessage();
		//if(player.getWeapon())
		monster.actions(this);
		//player.turnEnd();
		//monster.turnEnd();
	}

	public void end() {

		//player.setEXPGold(monster.getEXP(), floor);
	}

	public Difficulty getDifficulty() {
		return d;
	}

	public Player getPlayer() {
		return player;
	}

	public Monster getMonster() {
		return monster;
	}

	public int getFloor() {
		return floor;
	}

	public int getTurn() {
		return sumTurn;
	}

	public void victory() {
		//int EXP=monster.getEXP()

		player.setEXPGold(monster.getEXP(), monster.getGold(), monster.getTurn() > 0, monster.getOverHP() == 0);
	}

	/*public void setItem(Item item) {
		player.setItem(item);
		notifyObservers();
	}
	
	public void removeItem(int i) {
		player.removeItem(i);
		notifyObservers();
	}*/
	@Override
	public void addObserver(Observer o) {
		// TODO 自動生成されたメソッド・スタブ
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO 自動生成されたメソッド・スタブ
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		// TODO 自動生成されたメソッド・スタブ
		for (Observer o : observers)
			o.update(this);
	}

	@Override
	public void turnStart() {
		// TODO 自動生成されたメソッド・スタブ
		//for (SystemItem o : systemItems)
		//o.turnStart(this);
	}

	@Override
	public void commandWait() {
		// TODO 自動生成されたメソッド・スタブ
		for (Observer o : observers)
			o.commandWait();
	}

	@Override
	public void commandStart() {
		// TODO 自動生成されたメソッド・スタブ
		for (Observer o : observers)
			o.commandStart();
	}
}
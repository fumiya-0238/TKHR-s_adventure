package com.example.myapp.repository.debug;

public class MonsterData extends TKHRData {
	private String name;
	private int hp;
	private int overHp;
	private int attack;
	private int exp;
	private int gold;
	private int turn;
	private String text;

	public MonsterData(int id, String name, int hp, int overHp, int attack, int exp, int gold, int turn, String text) {
		super(id);
		this.name = name;
		this.hp = hp;
		this.overHp = overHp;
		this.attack = attack;
		this.exp = exp;
		this.gold = gold;
		this.turn = turn;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getOverHp() {
		return overHp;
	}

	public int getAttack() {
		return attack;
	}

	public int getExp() {
		return exp;
	}

	public int getGold() {
		return gold;
	}

	public int getTurn() {
		return turn;
	}

	public String getText() {
		return text;
	}
}

package com.example.myapp.repository.book;

public class MonsterPage extends BookPage {
	private int hp;
	private int overHp;
	private int attack;
	private int exp;
	private int gold;
	private int turn;

	public MonsterPage(int id, String name, int hp, int overHp, int attack, int exp, int gold, int turn) {
		super(id, name);
		this.hp = hp;
		this.overHp = overHp;
		this.attack = attack;
		this.exp = exp;
		this.gold = gold;
		this.turn = turn;
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

}

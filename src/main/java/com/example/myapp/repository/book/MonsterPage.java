package com.example.myapp.repository.book;

import com.example.myapp.creater.CreateText;

public class MonsterPage extends BookPage {
	private int hp;
	private int overHp;
	private int attack;
	private int exp;
	private int gold;
	private int turn;

	public MonsterPage(int id, String name, int hp, int overHp, int attack, int exp, int gold, int turn) {
		super(id, name);
		defaultPageName("monster");
		this.hp = hp;
		this.overHp = overHp;
		this.attack = attack;
		this.exp = exp;
		this.gold = gold;
		this.turn = turn;
	}

	public MonsterPage(MonsterPage page) {
		super(page);
		setPageName(page.getPageName());
		hp = page.getHp();
		overHp = page.getOverHp();
		attack = page.getAttack();
		exp = page.getExp();
		gold = page.getGold();
		turn = page.getTurn();
	}

	public void setParam(int columnId, String after) {
		switch (columnId) {
		case 1:
			name = after;
			break;
		case 2:
			hp = Integer.parseInt(after);
			break;
		case 3:
			overHp = Integer.parseInt(after);
			break;
		case 4:
			attack = Integer.parseInt(after);
			break;
		case 5:
			exp = Integer.parseInt(after);
			break;
		case 6:
			gold = Integer.parseInt(after);
			break;
		case 7:
			CreateText.INSTANCE.macherFind(this, after);
			break;
		}
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

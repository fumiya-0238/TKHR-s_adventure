package tkhr.monsters;

import java.util.List;

import tkhr.Battle;
import tkhr.monsters.conditions.MonsterCondition;

public abstract class Monster {
	protected String name;
	protected int HP;
	protected int MAXHP;
	protected int OverHP;
	protected int MAXOverHP;
	protected int ATK;
	protected int EXP;
	protected int Gold;
	protected int Turn;
	protected int ID;
	protected boolean death;
	protected List<MonsterCondition> conditions;

	public void init() {
		MAXHP = HP;
		MAXOverHP = OverHP;
	}

	public String calcDamageResult(int damage, boolean penetrate, Battle battle) {
		int resultDamage = simulateDamage(damage, penetrate, battle);
		String text = name + "に" + resultDamage + "ダメージを与えた。";
		setDamage(resultDamage);
		return text;
	}

	public abstract int simulateDamage(int damage, boolean penetrate, Battle battle);

	public abstract void actions(Battle battle);

	public void templeactions(Battle battle) {
	}

	public abstract void death(Battle battle);

	public String standardDeath(Battle battle) {
		//text.append(getName() + "を倒した。");

		battle.getPlayer().setEXPGold(EXP, Gold, Turn > 0, OverHP == 0);
		return "";
	}

	public void turnEnd() {
	}

	public String getName() {
		return name;
	}

	public int getHP() {
		return HP;
	}

	public int getOverHP() {
		return OverHP;
	}

	public void setTurn(int turn) {
		this.Turn += turn;
	}

	private void setDamage(int damage) {
		HP -= damage;
		OverHP -= damage;
		if (HP < 0) {
			HP = 0;
			if (OverHP < 0)
				OverHP = 0;
		}
	}

	public void setHeal(int heal) {
		HP += heal;
		OverHP += heal;
		if (HP > MAXHP) {
			HP = MAXHP;
			OverHP = MAXOverHP;
		}
	}

	public int getATK() {
		return ATK;
	}

	public int getEXP() {
		return EXP;
	}

	public int getGold() {
		return Gold;
	}

	public int getTurn() {
		return Turn;
	}

	public int getID() {
		return ID;
	}

	public void downTurn() {
		Turn--;
	}

	public void plusMonsterCondition(String name) {
		for (MonsterCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.plusAmount();
			}
		}
	}
	public void plusMonsterCondition(String name,int amount) {
		for (MonsterCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.setAmount(amount);
			}
		}
	}
	public void minusPlayerCondition(String name) {
		for (MonsterCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.minusAmount();
			}
		}
	}

	public void endMonsterCondition(String name) {
		for (MonsterCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.turnEnd();
			}
		}
	}

	public int amountPlayerCondition(String name) {
		for (MonsterCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				return condition.getAmount();
			}
		}
		return 0;
	}
}

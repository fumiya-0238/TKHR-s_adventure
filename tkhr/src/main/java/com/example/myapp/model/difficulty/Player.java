package tkhr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tkhr.items.Item;
import tkhr.playerConditions.PlayerCondition;
import tkhr.weapons.Weapon;
import tkhr.weapons.素手;

public class Player {
	private int LV;
	private int HP;
	private int MAXHP;
	private int ATK;
	private List<Item> items;
	private Weapon weapon;
	private int tension;
	private int EXP;
	private int sumEXP;
	private int Gold;
	private int defaultCritical;
	private int critical;
	private int action;
	final private int maxItem = 20;
	private int boost;
	private List<PlayerCondition> conditions;

	public Player() {
		items = new ArrayList<Item>();
		resetCritical();
	}
	public void resetStatus() {
		items.clear();
		equip(new 素手());
		defaultCritical=1;
		critical=1;
		tension=0;
		Gold=0;
		boost=0;
		//こんぢしょん
	}
	public int getLV() {
		return LV;
	}

	public int getHP() {
		return HP;
	}

	public int getMAXHP() {
		return MAXHP;
	}

	public void setLV(int LV) {
		this.LV = LV;
		HP = MAXHP = LV * 2 + 8;
		ATK = LV + 1 + weapon.getATK();
		EXP = LV * 2 - 1;
	}

	public int getMaxItem() {
		return maxItem;
	}

	public String calcDamageResult(int damage, boolean penetrate, Battle battle) {
		if (action == 4 && !penetrate) {
			damage = 0;
		}
		String text = damage + "を受けた。";
		return text;
	}

	protected void setDamage(int damage) {
		HP -= damage;
		if (HP < 0) {
			HP = 0;
		}
	}

	public String healResult(int n) {
		HP += n;
		if (MAXHP < HP)
			HP = MAXHP;
		return "自分のHPが" + n + "回復した。";
	}

	public void turnEnd() {

	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical += critical;
	}
	public void setDefaultCritical(int d) {
		defaultCritical=d;
	}
	public void resetCritical() {
		critical = defaultCritical;
	}

	public int getATK() {
		return ATK;
	}

	public int getBoost() {
		return boost;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItem(Item item) {
		if (items.size() < maxItem)
			items.add(item);
	}

	public void removeItem(int i) {
		items.remove(i);
	}

	public Weapon getWeapon() {
		return weapon;
	}
	/*public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}*/

	public String setTension(int tension) {
		if (this.tension < 100)
			this.tension += tension;
		return "気合が" + tension + "になった。";
	}

	public void resetTension() {
		tension = 0;
	}

	public int getTension() {
		return tension;
	}

	public int getEXP() {
		return EXP;
	}

	public int getsumEXP() {
		return sumEXP;
	}

	public int getGold() {
		return Gold;
	}

	public void levelUp() {
		LV++;
		HP += 2;
		MAXHP += 2;
		ATK++;
		EXP += LV * 2 - 1;
		if (EXP < 1)
			levelUp();
	}

	public void equip(Weapon weapon) {
		if(Objects.nonNull(this.weapon)) {
		ATK -= this.weapon.getATK();
		if (!this.weapon.getName().equals(""))
			setItem(this.weapon);
		}
		this.weapon = weapon;
		ATK += weapon.getATK();
	}

	public void setEXPGold(int EXP, int Gold, boolean turn, boolean over) {
		double bonus = 1.0;
		if (turn && over) {
			bonus = 1.5;
		} else if (turn || over) {
			bonus = 1.3;
		}
		EXP = (int) (EXP * bonus);
		Gold = (int) (Gold * bonus);
		this.Gold += Gold;
		this.sumEXP += EXP;
		this.EXP -= EXP;
		if (this.EXP < 1)
			levelUp();
	}

	public void plusPlayerCondition(String name) {
		for (PlayerCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.plusAmount();
			}
		}
	}

	public void minusPlayerCondition(String name) {
		for (PlayerCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				condition.minusAmount();
			}
		}
	}

	public int amountPlayerCondition(String name) {
		for (PlayerCondition condition : conditions) {
			if (condition.getName().equals(name)) {
				return condition.getAmount();
			}
		}
		return 0;
	}

	public Item descSearchItem(String name) {
		for (int i = items.size() - 1; i > 0; i--) {
			if (items.get(i).getName().contains(name)) {
				return items.get(i);
			}
		}
		return null;
	}

	public int countItem(String name) {
		int n = 0;
		for (int i = 0, l = items.size(); i < l; i++) {
			if (items.get(i).getName().contains(name)) {
				n++;
			}
		}
		return n;
	}
}

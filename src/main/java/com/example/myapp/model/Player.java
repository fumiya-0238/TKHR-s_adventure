
package com.example.myapp.model;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.SubscriptionItem;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class Player extends Living {
	private int lv;
	private List<Item> items;
	private List<Weapon> weapons;
	private int tension;
	private int exp;
	private int sumExp;
	private int defaultCritical;
	private int critical;
	private int action;
	final private int maxItem = 20;
	private int boost;
	private int plusBoost = 2;
	private boolean boostOn;

	public Player() {
		name = "プレイヤー";
		items = new ArrayList<>();
		weapons = new ArrayList<>();
	}
	
	public void resetStatus() {
		weapons.add(CreateWeapon.INSTANCE.create(1));
		int maxHp = lv * 2 + 8;
		setAttack(lv + 1);
		setEXP(lv * 2 - 1);
		setDefaultCritical(1);
		setCritical(1);
		setTension(0);
		setGold(0);
		setBoost(0);
		boostOn = false;
		items.clear();
		conditions.clear();
		setHP(maxHp);
		setMAXHP(maxHp);
		setSumEXP(0);
		equip(CreateWeapon.INSTANCE.create(28));
		equipSubWeapon(CreateWeapon.INSTANCE.create(24));
		/*
		setItem(CreateItem.INSTANCE.create(1));
		setItem(CreateItem.INSTANCE.create(2));
		setItem(CreateItem.INSTANCE.create(4));
		setItem(CreateItem.INSTANCE.create(5));
		setItem(CreateItem.INSTANCE.create(8));
		setItem(CreateItem.INSTANCE.create(13));
		setItem(CreateItem.INSTANCE.create(14));
		setItem(CreateItem.INSTANCE.create(11));
		setItem(CreateItem.INSTANCE.create(15));
		setItem(CreateItem.INSTANCE.create(17));
		setItem(CreateItem.INSTANCE.create(28));
		setItem(CreateItem.INSTANCE.create(30));
		setItem(CreateItem.INSTANCE.create(9));
		setItem(CreateItem.INSTANCE.create(24));
		setItem(CreateItem.INSTANCE.create(26));
		setItem(CreateItem.INSTANCE.create(18));
		setItem(CreateItem.INSTANCE.create(20));
		setItem(CreateItem.INSTANCE.create(27));
		setItem(CreateItem.INSTANCE.create(31));
		setItem(CreateItem.INSTANCE.create(32));*/
	}
	private void commonAction(Battle battle, List<ActionInfo> infos, int n) {
		for (int i = n; i <= n; i++) {
			ActionInfo info = infos.get(i);
			if (info.getAttackIs()) {
				info.getLiving().calcDamage(battle, infos, i);
			} else if (0 < info.getDamage()) {
				info.getLiving().calcHeal(battle, infos, i);
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
	}

	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.setLiving(battle.getMonster());
		info.addMessages("プレイヤーは攻撃をした");
		infos.add(info);
		for (Condition condition : conditions) {
			condition.attack(battle, this, infos, n);
		}

		for (Weapon weapon : weapons) {
			weapon.attack(battle, infos, n);
		}

		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, infos, n);
		}
		commonAction(battle, infos, n);
	}

	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.setLiving(battle.getMonster());
		info.addMessages("プレイヤーは手加減攻撃をした");
		infos.add(info);
		//attackMulti
		//attackPlus
		for (Condition condition : conditions) {
			condition.attack(battle, this, infos, n);
		}
		for (Weapon weapon : weapons) {
			weapon.weekAttack(battle, infos, n);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, infos, n);
		}
		commonAction(battle, infos, n);
	}

	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.setLiving(battle.getMonster());
		info.addMessages("プレイヤーは強攻撃をした");
		infos.add(info);
		for (Condition condition : conditions) {
			condition.attack(battle, this, infos, n);
		}
		for (Weapon weapon : weapons) {
			weapon.criticalAttack(battle, infos, n);
		}
		for (Condition condition : conditions) {
			condition.criticalAttack(battle, this, infos, n);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, infos, n);
		}
		commonAction(battle, infos, n);
	}

	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.setLiving(battle.getMonster());
		info.addMessages("プレイヤーは防御をした");
		infos.add(info);
		for (Weapon weapon : weapons) {
			weapon.defence(battle, infos, n);
		}
		commonAction(battle, infos, n);
	}

	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.setLiving(battle.getMonster());
		info.addMessages("プレイヤーは気合をためた");
		infos.add(info);
		for (Weapon weapon : weapons) {
			weapon.tension(battle, infos, n);
		}
		commonAction(battle, infos, n);
	}

	@Override
	public void death(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		for (Condition condition : conditions) {
			condition.death(battle, this, infos, n);
		}
		if (hp == 0) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ゲームオーバー, ""));
		}
	}

	public void toggleBoost() {
		boostOn = !boostOn;
	}

	public void turnEnd() {

	}

	public void setItem(Item item) {
		if (items.size() < maxItem) {
			items.add(item);
			Battle.addLogs(new ScreenChange(ScreenEnum.アイテム追加, item.getName()));
		}
	}

	public void removeItem(Battle battle, int i) {
		if (items.get(i) instanceof SubscriptionItem) {
			((SubscriptionItem) items.get(i)).cancel(battle);
		}
		items.remove(i);
		Battle.addLogs(new ScreenChange(ScreenEnum.アイテム削除, String.valueOf(i)));
	}
	/*public String setTension(int tension) {
		this.tension += tension;
		if (this.tension < 100) {
			this.tension += tension;
		}
		return "気合が" + tension + "になった。";
	}*/

	public void levelUp() {
		lv++;
		hp += 2;
		maxHp += 2;
		attack++;
		exp += lv * 2 - 1;
		if (exp < 1) {
			levelUp();
		}
	}

	public void equip(Weapon weapon) {
		if (weapons.size() != 0) {
			for (Weapon takedWeapon : weapons) {
				takedWeapon.takeOff(this);
				attack -= takedWeapon.getAttack();
			}
		}
		weapons.clear();
		weapon.equip(this);
		weapons.add(weapon);
		setAttack(attack + weapon.getAttack());
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器, String.valueOf(weapon.getName())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, ""));
		//Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器攻撃力, String.valueOf(weapon.getAttack())));
		
	}

	public void equipSubWeapon(Weapon weapon) {
		weapon.equip(this);
		weapons.add(weapon);
		weapon.setAttack(0);
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, String.valueOf(weapon.getName())));
	}

	public void setEXPGold(int EXP, int Gold, boolean turn, boolean over) {
		if (hp == 0) {
			return;
		}
		double bonus = 1.0;
		if (turn && over) {
			bonus = 1.5;
		} else if (turn || over) {
			bonus = 1.3;
		}
		EXP = (int) (EXP * bonus);
		Gold = (int) (Gold * bonus);
		this.gold += Gold;
		plusSumEXP(EXP);
		this.sumExp += EXP;
		this.exp -= EXP;
		if (this.exp < 1) {
			levelUp();
		}
		setLV(lv);
		setHP(hp);
		setMAXHP(maxHp);
		setAttack(attack);
		setEXP(exp);
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

	@Override
	public void setDamage(int damage) {
		int n = hp - damage;
		if (n < 0) {
			n = 0;
		}
		setHP(n);
	}

	@Override
	public void setHeal(int heal) {
		// TODO 自動生成されたメソッド・スタブ
		int n = hp + heal;
		if (n < 0) {
			n = 0;
		}
		if (maxHp < n) {
			n = maxHp;
		}
		setHP(n);
	}

	public void setLV(int lv) {
		this.lv = lv;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーLV, String.valueOf(lv)));
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーHP, String.valueOf(hp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー最大HP, String.valueOf(maxHp)));
	}

	public void setTension(int tension) {
		this.tension = tension;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンション, String.valueOf(this.tension)));
	}

	public void plusTension(Battle battle, List<ActionInfo> infos, int n, int tension) {
		int t = this.tension + tension;
		if (t > 100) {
			t = 100;
		}
		setTension(t);
		//return "気合が" + this.tension + "になった。";
	}

	public void resetTension() {
		setTension(0);
	}

	public void plusBoost(int boost) {
		int n = this.boost = boost;
		if (n > 100) {
			n = 100;
		}
		setBoost(n);
	}

	public void setBoost(int boost) {
		this.boost = boost;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーブースト, String.valueOf(boost)));
	}

	public void plusSumEXP(int exp) {
		setSumEXP(sumExp + exp);
	}

	public void setSumEXP(int sumExp) {
		this.sumExp = sumExp;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー合計経験値, String.valueOf(sumExp)));
	}

	public void setCritical(int critical) {
		this.critical = critical;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー強攻撃, String.valueOf(critical)));
	}

	public void plusCritical(int critical) {
		setCritical(this.critical + critical);
	}

	public void resetCritical() {
		setCritical(defaultCritical);
	}

	public void setDefaultCritical(int defaultCritical) {
		this.defaultCritical = defaultCritical;
	}

	public void plusDefaultCritical(int defaultCritical) {
		setDefaultCritical(this.defaultCritical + defaultCritical);
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getLV() {
		return lv;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public int getTension() {
		return tension;
	}

	public int getEXP() {
		return exp;
	}

	public int getsumEXP() {
		return sumExp;
	}

	public int getCritical() {
		return critical;
	}

	public int getBoost() {
		return boost;
	}

	public boolean getBoostOn() {
		return boostOn;
	}

	public List<Item> getItems() {
		return items;
	}

	public int getMaxItem() {
		return maxItem;
	}

	public int getAction() {
		return action;
	}
}

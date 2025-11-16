
package com.example.myapp.model;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
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
	private int sumExp;
	private int defaultCritical;
	private int critical;
	private int action;
	private int maxItem = 20;
	private int boost;
	private int plusBoost = 0;
	private int multiBoost = 1;
	private double priceMulti;
	private boolean boostOn;
	private int bonus;
	private List<Boolean> commandClickFlags;

	public Player() {
		name = "プレイヤー";
		items = new ArrayList<>();
		weapons = new ArrayList<>();
		commandClickFlags = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			commandClickFlags.add(true);
		}
	}

	public void resetStatus() {
		weaponReset();
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
		Battle.addLogs(new ScreenChange(ScreenEnum.毒が回復, ""));
		conditions.clear();
		setMAXHP(maxHp);
		setHP(maxHp);
		setSumEXP(0);
		plusBoost = 0;
		multiBoost = 1;
		priceMulti = 1;
		attackMulti = 1;
		attackPlus = 0;
		clearCommands();
		equip(CreateWeapon.INSTANCE.create(27));
		equipSubWeapon(CreateWeapon.INSTANCE.create(12));
		//setItem(CreateItem.INSTANCE.create(32));
		//equip(CreateWeapon.INSTANCE.create(32));
		/*
		
		equipSubWeapon(CreateWeapon.INSTANCE.create(22));
		//equip(CreateWeapon.INSTANCE.create(2));
		setItem(CreateItem.INSTANCE.create(26));
		setItem(CreateItem.INSTANCE.create(12));
		setItem(CreateItem.INSTANCE.create(12));
		setItem(CreateItem.INSTANCE.create(12));
		setItem(CreateItem.INSTANCE.create(12));
		setItem(CreateItem.INSTANCE.create(12));
		setItem(CreateItem.INSTANCE.create(26));
		setItem(CreateItem.INSTANCE.create(16));
		setItem(CreateItem.INSTANCE.create(16));
		setItem(CreateItem.INSTANCE.create(16));*/

	}

	public void attack(Battle battle, List<ActionInfo> infos) {
		if (boostOn) {
			boostAttack(battle, infos);
			return;
		}
		plusBoost(finalBoostPlus());
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		String message = "プレイヤーは攻撃をした";
		info.addMessages(message);
		info.setActionType(4);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.attack(battle, attackInfos);

		}
		double tension = 1.0 + ((double) this.tension / 100);
		resetTension();
		info.setNumber((int) (attack * tension));
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));
		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}
		infos.addAll(attackInfos);

		battle.commonAction(infos);
	}

	public void weekAttack(Battle battle, List<ActionInfo> infos) {
		if (boostOn) {
			boostWeekAttack(battle, infos);
			return;
		}
		plusBoost(finalBoostPlus());
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		info.setActionType(4);
		String message = "プレイヤーは手加減攻撃をした";
		info.addMessages(message);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.weekAttack(battle, attackInfos);
		}
		info.setNumber(attack - 1);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void criticalAttack(Battle battle, List<ActionInfo> infos) {
		if (boostOn) {
			boostCriticalAttack(battle, infos);
			return;
		}
		plusBoost(finalBoostPlus());
		double tension = 1.0 + ((double) this.tension / 100);
		plusCritical(-1);
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		double attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		info.setActionType(4);
		String message = "プレイヤーは強攻撃をした";
		info.addMessages(message);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.criticalAttack(battle, attackInfos);
		}
		attack = attack * 1.5;
		info.setNumber(attack * tension);
		resetTension();
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.criticalAttack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}

		resetTension();
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void defence(Battle battle, List<ActionInfo> infos) {
		if (boostOn) {
			boostDefence(battle, infos);
			return;
		}
		plusBoost(finalBoostPlus());
		List<ActionInfo> defenceInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは防御をした";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		defenceInfos.add(info);
		plusCondition(battle, defenceInfos, ConditionEnum.防御);
		for (Weapon weapon : weapons) {
			weapon.defence(battle, defenceInfos);
		}

		for (Condition condition : conditions) {
			condition.defence(battle, this, defenceInfos);
		}

		infos.addAll(defenceInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);

	}

	public void tension(Battle battle, List<ActionInfo> infos) {
		if (boostOn) {
			boostTension(battle, infos);
			return;
		}
		plusBoost(finalBoostPlus());
		List<ActionInfo> tensionInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(25);
		info.setActionType(3);
		String message = "プレイヤーは気合をためた";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		tensionInfos.add(info);
		for (Weapon weapon : weapons) {
			weapon.tension(battle, tensionInfos);
		}
		for (Condition condition : conditions) {
			condition.tension(battle, tensionInfos);
		}
		infos.addAll(tensionInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);
	}

	public void boostAttack(Battle battle, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		double tension = 1.0 + ((double) this.tension / 100);
		setBoost(0);
		boostOn = false;
		List<ActionInfo> attackInfos = new ArrayList<>();
		int[] status = { 0, -1 };
		plusCondition(battle, attackInfos, ConditionEnum.貫通, status);
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		String message = "プレイヤーは攻撃をした";
		info.addMessages(message);
		info.setActionType(4);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.attack(battle, attackInfos);

		}
		resetTension();
		info.setNumber(attack * 1.5 * tension);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));
		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}
		infos.addAll(attackInfos);

		battle.commonAction(infos);
	}

	public void boostWeekAttack(Battle battle, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		double tension = 1.0 + ((double) this.tension / 100);
		setBoost(0);
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		info.setActionType(4);
		String message = "プレイヤーは手加減攻撃をした";
		info.addMessages(message);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.weekAttack(battle, attackInfos);
		}
		info.setNumber((attack - 1) * tension);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void boostCriticalAttack(Battle battle, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		double tension = 1.0 + ((double) this.tension / 100);
		setBoost(0);
		boostOn = false;
		plusCritical(-1);
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		double attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getMonster());
		info.setActionType(4);
		String message = "プレイヤーは強攻撃をした";
		info.addMessages(message);
		attackInfos.add(info);
		for (Weapon weapon : weapons) {
			attack += weapon.criticalAttack(battle, attackInfos);
		}

		info.setNumber(attack * 2 * tension);
		resetTension();
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, ""));

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.criticalAttack(battle, this, attackInfos);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos);
		}

		resetTension();
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void boostDefence(Battle battle, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		setBoost(0);
		boostOn = false;
		List<ActionInfo> defenceInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは防御をした";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		defenceInfos.add(info);
		int[] status = { 1, 1 };
		plusCondition(battle, defenceInfos, ConditionEnum.防御, status);

		for (Weapon weapon : weapons) {
			weapon.defence(battle, defenceInfos);
		}

		for (Condition condition : conditions) {
			condition.defence(battle, this, defenceInfos);
		}

		infos.addAll(defenceInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);

	}

	public void boostTension(Battle battle, List<ActionInfo> infos) {
		Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
		setBoost(0);
		boostOn = false;
		List<ActionInfo> tensionInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(100);
		info.setActionType(3);
		String message = "プレイヤーは気合をためた";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		tensionInfos.add(info);
		for (Weapon weapon : weapons) {
			weapon.tension(battle, tensionInfos);
		}
		for (Condition condition : conditions) {
			condition.tension(battle, tensionInfos);
		}
		infos.addAll(tensionInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);
	}

	@Override
	public void death(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp == 0) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ゲームオーバー, ""));
		}
	}

	public void toggleBoost() {
		boostOn = !boostOn;
	}

	public void setItem(Item item) {
		if (items.size() < maxItem) {
			items.add(item);
			Battle.addLogs(new ScreenChange(ScreenEnum.アイテム追加, item.getName()));
		}
	}

	public void removeItem(int i) {
		items.remove(i);
		Battle.addLogs(new ScreenChange(ScreenEnum.アイテム削除, String.valueOf(i)));
	}

	public void removeItem(Item item) {
		for (int i = 0, l = items.size(); i < l; i++) {
			if (items.get(i) == item) {
				removeItem(i);
				break;
			}
		}
	}

	public void levelUp() {
		lv++;
		hp += 2;
		maxHp += 2;
		attack++;
		exp += lv * 2 - 1;
		if (exp <= 0) {
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
		int x = descSearchItem(32);
		if (x != -1) {
			int weaponId1 = weapons.get(0).getId();
			int weaponId2 = weapon.getId();
			if ((weaponId1 == 26 && weaponId2 == 27) || (weaponId1 == 27 && weaponId2 == 26)) {
				weapon = CreateWeapon.INSTANCE.create(31);
				removeItem(x);
			}
		}

		weapons.clear();
		weapon.equip(this);
		weapons.add(weapon);
		setAttack(attack + weapon.getAttack());
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器, String.valueOf(weapon.getName())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, ""));
	}

	public void equipSubWeapon(Weapon weapon) {
		weapon.equip(this);
		int size = weapons.size();
		if (size == 1) {
			weapons.add(weapon);
		} else {
			weapons.get(1).takeOff(this);
			weapons.set(1, weapon);
		}

		weapon.setAttack(0);
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, String.valueOf(weapon.getName())));
	}

	public void setEXPGold(int EXP, int Gold, boolean turn, boolean over) {
		if (hp == 0) {
			return;
		}
		double bonus = 1.0;

		int b = 0;
		if (turn && over) {
			b = 2;
		} else if (turn || over) {
			b = 1;
		}
		b += amountCondition(ConditionEnum.真珠);
		b += amountCondition(ConditionEnum.スライム状態);

		if (2 <= b) {
			bonus = 1.5;
			b = 2;
		} else if (b == 1) {
			bonus = 1.3;
		}
		EXP = (int) (EXP * bonus);
		Gold = (int) (Gold * bonus);
		this.gold += Gold;
		this.sumExp += EXP;
		this.exp -= EXP;
		boolean levelFlag = false;
		if (this.exp <= 0) {
			levelUp();
			levelFlag = true;
		}
		setLV(lv);
		setHP(hp);
		setMAXHP(maxHp);
		setAttack(attack);
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー経験値, String.valueOf(exp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー合計経験値, String.valueOf(sumExp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーお金, String.valueOf(gold)));

		Battle.addLogs(new ScreenChange(ScreenEnum.リザルト, String.valueOf(EXP) + "#" + String.valueOf(Gold) + "#" + b));
		if (levelFlag) {
			Battle.addLogs(new ScreenChange(ScreenEnum.レベルアップメッセージ, String.valueOf(lv)));
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
	}

	public void weaponReset() {
		for (Weapon weapon : weapons) {
			weapon.takeOff(this);
		}
		weapons.clear();
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, ""));
		weapons.add(CreateWeapon.INSTANCE.create(1));
	}

	public void conditionnReset() {
		for (Weapon weapon : weapons) {
			weapon.takeOff(this);
		}
		weapons.clear();
		weapons.add(CreateWeapon.INSTANCE.create(1));
	}

	public int descSearchItem(String name) {
		for (int i = items.size() - 1; i >= 0; i--) {
			if (items.get(i).getName().contains(name)) {
				return i;
			}
		}
		return -1;
	}

	public int descSearchItem(int itemId) {
		for (int i = items.size() - 1; i >= 0; i--) {
			if (items.get(i).getId() == itemId) {
				return i;
			}
		}
		return -1;
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
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーダメージ, String.valueOf(damage)));
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
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー回復, String.valueOf(heal)));
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
		int percent = hp * 100 / maxHp;
		if (percent <= 10) {
			Battle.addLogs(new ScreenChange(ScreenEnum.HPがピンチ, ""));
		} else if (percent <= 50) {
			Battle.addLogs(new ScreenChange(ScreenEnum.HPが半分以下, ""));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.HPが普通, ""));
		}
	}

	public void setTension(int tension) {
		this.tension = tension;
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンション, String.valueOf(tension)));
	}

	public void plusTension(Battle battle, List<ActionInfo> infos) {
		int up = infos.get(0).getFinalNumber();
		int t = this.tension + up;
		if (t > 100) {
			t = 100;
		}
		infos.get(0).addMessages("気合が" + t + "になった");
		setTension(t);
		if (0 < up) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンションアップ, String.valueOf(up)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンションダウン, String.valueOf(up)));
		}
		//return "気合が" + this.tension + "になった。";
	}

	public void resetTension() {
		setTension(0);
	}

	public void plusMultiBoost(double multiBoost) {
		this.multiBoost *= multiBoost;
	}

	public int finalBoostPlus() {
		return (2 + plusBoost) * multiBoost;
	}

	public void plusBoost(int boost) {
		int n = this.boost + boost;
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

	public double getPriceMulti() {
		return priceMulti;
	}

	public void plusPriceMulti(double priceMulti) {
		this.priceMulti *= priceMulti;
	}

	public void setPriceMulti(double priceMulti) {
		this.priceMulti = priceMulti;
	}

	public void plusBonus() {
		bonus++;
	}

	public void resetBonus() {
		bonus = 0;
	}

	public List<Boolean> getCommandClickFlags() {
		return commandClickFlags;
	}

	public void clearCommands() {
		for (int i = 0; i < 5; i++) {
			commandClickFlags.set(i, true);
		}
	}

	public void setCommandFalse(int n) {
		commandClickFlags.set(n, false);
	}
}

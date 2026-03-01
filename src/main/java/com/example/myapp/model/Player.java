
package com.example.myapp.model;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class Player extends Living {
	private List<Item> items;
	private List<Weapon> weapons;
	private int weaponAttack;
	private int tension;
	private int sumExp;
	private int defaultCritical;
	private int critical;
	private int action;
	private int maxItem = 20;
	private boolean itemSwtchFlag;
	private int itemPage;
	private int boost;
	private int plusBoost = 0;
	private int multiBoost = 1;
	private double priceMulti;
	private double priceMultiItem;
	private double debugPrice;
	private boolean boostOn;
	private List<Boolean> commandClickFlags;
	private int itemUse;

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
		itemPage = 0;
		setAttack(lv + 1);
		setEXP(lv * 2 - 1);
		setDefaultCritical(1);
		setCritical(1);
		setTension(0);
		setGold(0);
		setBoost(0);
		boostOn = false;
		itemSwtchFlag = false;
		maxItem = 20;
		Battle.addLogs(new ScreenChange(ScreenEnum.アイテム切り替えボタン削除, ""));
		items.clear();
		Battle.addLogs(new ScreenChange(ScreenEnum.毒が回復, ""));
		conditions.clear();
		setMAXHP(maxHp);
		setHP(maxHp);
		setSumEXP(0);
		plusBoost = 0;
		multiBoost = 1;
		priceMulti = 1;
		priceMultiItem = 1;
		attackMulti = 1;
		attackPlus = 0;
		debugPrice = 1;
		clearCommands();
	}

	public void attack(Battle battle, List<ActionInfo> infos, ActionTypeEnum actionType, Living receiver) {
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(receiver);
		String message = "プレイヤーは攻撃をした";
		info.addMessages(message, this);
		info.setActionType(actionType);
		for (Weapon weapon : weapons) {
			attack += weapon.attack(battle, attackInfos);

		}
		double tension = 1.0 + ((double) this.tension / 100);
		resetTension(battle);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, String.valueOf(((Monster) receiver).getNumber())));
		if (boostOn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
			int[] status = { 0, -1 };
			plusCondition(battle, attackInfos, ConditionEnum.貫通, status);
			setBoost(0);
			info.setNumber(attack * 1.5 * tension);
			boostOn = false;
		} else {
			plusBoost(finalBoostPlus());
			info.setNumber(attack * tension);
		}

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos, info);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos, info);
		}
		if (battle.getDungeon().isRandomMode()) {
			double x = 0.8 + Math.random() * 0.2;
			info.setNumber(info.getNumber() * x);
		}

		attackInfos.add(0, info);
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void weekAttack(Battle battle, List<ActionInfo> infos) {
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getTargetMonster());
		String message = "プレイヤーは手加減攻撃をした";
		info.addMessages(message);
		info.setActionType(ActionTypeEnum.プレイヤー手加減);
		for (Weapon weapon : weapons) {
			attack += weapon.weekAttack(battle, attackInfos);

		}

		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, String.valueOf(battle.getTarget())));
		if (boostOn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
			setBoost(0);
			double tension = 1.0 + ((double) this.tension / 100);
			info.setNumber((attack - 1) * tension);
			boostOn = false;
		} else {
			plusBoost(finalBoostPlus());
			info.setNumber(attack - 1);
		}

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos, info);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos, info);
		}

		attackInfos.add(0, info);
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void criticalAttack(Battle battle, List<ActionInfo> infos) {
		List<ActionInfo> attackInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		int attack = getFinalAttack();
		info.setAttacker(this);
		info.setReceiver(battle.getTargetMonster());
		String message = "プレイヤーは強攻撃をした";
		info.addMessages(message);
		info.setActionType(ActionTypeEnum.プレイヤー強攻撃);
		for (Weapon weapon : weapons) {
			attack += weapon.criticalAttack(battle, attackInfos);

		}
		double tension = 1.0 + ((double) this.tension / 100);
		resetTension(battle);
		plusCritical(-1);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		Battle.addLogs(new ScreenChange(ScreenEnum.攻撃エフェクト, String.valueOf(battle.getTarget())));
		if (boostOn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
			setBoost(0);
			info.setNumber(attack * 2 * tension);
			boostOn = false;
		} else {
			plusBoost(finalBoostPlus());
			info.setNumber(attack * 1.5 * tension);
		}

		for (Condition condition : conditions) {
			condition.attack(battle, this, attackInfos, info);
		}
		for (Condition condition : conditions) {
			condition.criticalAttack(battle, this, attackInfos, info);
		}
		for (Condition condition : conditions) {
			condition.damagePlus(battle, this, attackInfos, info);
		}
		if (battle.getDungeon().isRandomMode()) {
			double x = 0.8 + Math.random() * 0.2;
			info.setNumber(info.getNumber() * x);
		}
		attackInfos.add(0, info);
		infos.addAll(attackInfos);
		battle.commonAction(infos);
	}

	public void defence(Battle battle, List<ActionInfo> infos) {
		List<ActionInfo> defenceInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは防御をした";
		info.addMessages(message, this);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		if (boostOn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
			setBoost(0);
			int[] status = { 1, 1 };
			plusCondition(battle, defenceInfos, ConditionEnum.防御, status);
			boostOn = false;
		} else {
			plusBoost(finalBoostPlus());
			plusCondition(battle, defenceInfos, ConditionEnum.防御);
		}
		for (Weapon weapon : weapons) {
			weapon.defence(battle, defenceInfos);
		}

		for (Condition condition : conditions) {
			condition.defence(battle, this, defenceInfos, info);
		}
		defenceInfos.add(0, info);
		infos.addAll(defenceInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);
	}

	public void tension(Battle battle, List<ActionInfo> infos) {
		List<ActionInfo> tensionInfos = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setActionType(ActionTypeEnum.テンション);
		String message = "プレイヤーは気合をためた";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		if (boostOn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ブーストオフ, ""));
			setBoost(0);
			info.setNumber(100);
			boostOn = false;
		} else {
			plusBoost(finalBoostPlus());
			info.setNumber(25);
		}
		for (Weapon weapon : weapons) {
			weapon.tension(battle, tensionInfos);
		}
		for (Condition condition : conditions) {
			condition.tension(battle, tensionInfos, info);
		}
		tensionInfos.add(0, info);
		infos.addAll(tensionInfos);
		Battle.addLogs(new ScreenChange(ScreenEnum.防御ウェイト, ""));
		battle.commonAction(infos);
	}

	public void useItem(Battle battle, int i, List<ActionInfo> infos) {
		i = i + itemPage * 20;
		Item item = items.get(i);
		ActionInfo info = new ActionInfo();
		String message = "プレイヤーは" + item.getName() + "を使った";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		infos.add(info);
		item.use(battle, infos);
		if (item.getActive()) {
			Battle.addLogs(new ScreenChange(ScreenEnum.アクティブアイテム, String.valueOf(i)));
		} else {
			removeItem(i);
		}
		for (Condition condition : conditions) {
			condition.useItem(battle, this, infos);
		}
		battle.commonAction(infos);
		battle.addMessageBatch(infos);
		battle.messageBatch();
		itemUse++;
	}

	public void resetItemUse() {
		itemUse = 0;
	}

	public int getItemUse() {
		return itemUse;
	}

	public void rightSwitchItem() {
		if (!itemSwtchFlag) {
			return;
		}
		if (itemPage == (maxItem - 1) / 20) {
			itemPage = 0;
		} else {
			itemPage++;
		}
		showItem();
	}

	public void leftSwitchItem() {
		if (!itemSwtchFlag) {
			return;
		}
		if (itemPage == 0) {
			itemPage = (maxItem - 1) / 20;
		} else {
			itemPage--;
		}
		showItem();
	}

	@Override
	public void death(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		if (hp == 0) {
			Battle.addLogs(new ScreenChange(ScreenEnum.待機, "500"));
			Battle.addLogs(new ScreenChange(ScreenEnum.ゲームオーバー, ""));
		}
	}

	public int getFinalAttack() {
		int finalAttack = (int) ((attack + attackPlus + weaponAttack) * attackMulti);
		if (999 < finalAttack) {
			finalAttack = 999;
		}
		return finalAttack;
	}

	public void updateWeaponAttack() {
		int sum = 0;
		for (Weapon weapon : weapons) {
			sum += weapon.getFinalAttack();
		}
		weaponAttack = sum;
		updateFinalAttack();
	}

	public void updateFinalAttack() {
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー攻撃力, String.valueOf(getFinalAttack())));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器攻撃力, String.valueOf(weaponAttack)));
	}

	public void toggleBoost() {
		boostOn = !boostOn;
	}

	public void addItem(Item item) {
		if (items.size() < maxItem) {
			items.add(item);
			item.addItem(this);
			if ((items.size() - 1) / 20 == itemPage) {
				showItem();
			}
			// Battle.addLogs(new ScreenChange(ScreenEnum.アイテム追加, item.getName()));
		}
	}

	public void addItem(Item item, int l) {
		for (int i = 0; i < l; i++) {
			items.add(item);
		}
		// if ((items.size() - 1) / 20 == itemPage) {
		showItem();
		// }
		// Battle.addLogs(new ScreenChange(ScreenEnum.アイテム追加, item.getName()));
	}

	public void removeItem(int i) {
		items.remove(i).removeItem(this);
		if (i / 20 == itemPage) {
			showItem();
		}
		// Battle.addLogs(new ScreenChange(ScreenEnum.アイテム削除, String.valueOf(i)));
	}

	public void showItem() {
		StringBuilder sb = new StringBuilder();
		// int size = items.size();
		sb.append(maxItem - itemPage * 20);
		sb.append("#");
		for (int i = itemPage * 20; i < (itemPage + 1) * 20; i++) {
			if (i < items.size() - 1) {
				String itemName = items.get(i).getName();
				sb.append(itemName + "#");
			} else if (i == items.size() - 1) {
				String itemName = items.get(i).getName();
				sb.append(itemName);
				break;
			} else {
				break;
			}
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.アイテム表示, sb.toString()));
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
		int upHp = 2 + countItem(30) * 3;
		hp += upHp;
		maxHp += upHp;
		attack++;
		exp += lv * 2 - 1;
		if (exp <= 0) {
			levelUp();
		}
	}

	public void levelDown() {
		if (lv == 1) {
			return;
		}
		lv--;
		hp -= 2;
		maxHp -= 2;
		attack--;
		exp = lv * 2 - 1;
	}

	public void equip(Weapon weapon) {
		if (weapons.size() != 0) {
			for (Weapon takedWeapon : weapons) {
				takedWeapon.takeOff(this);
			}
		}
		int x = descSearchItem(32);
		if (x != -1) {
			int weaponId1 = weapons.get(0).getId();
			int weaponId2 = weapon.getId();
			if ((weaponId1 == 27 && weaponId2 == 28) || (weaponId1 == 28 && weaponId2 == 27)) {
				weapon = CreateWeapon.INSTANCE.create(32);
				removeItem(x);
			} else if ((weaponId1 == 12 && weaponId2 == 26) || (weaponId1 == 26 && weaponId2 == 12)) {
				weapon = CreateWeapon.INSTANCE.create(34);
				removeItem(x);
			} else if ((weaponId1 == 13 && weaponId2 == 14) || (weaponId1 == 14 && weaponId2 == 13)) {
				weapon = CreateWeapon.INSTANCE.create(35);
				removeItem(x);
			} else if ((weaponId1 == 15 && weaponId2 == 21) || (weaponId1 == 21 && weaponId2 == 15)) {
				weapon = CreateWeapon.INSTANCE.create(36);
				removeItem(x);
			} else if ((weaponId1 == 18 && weaponId2 == 22) || (weaponId1 == 22 && weaponId2 == 18)) {
				weapon = CreateWeapon.INSTANCE.create(37);
				removeItem(x);
			} else if ((weaponId1 == 20 && weaponId2 == 23) || (weaponId1 == 23 && weaponId2 == 20)) {
				weapon = CreateWeapon.INSTANCE.create(38);
				removeItem(x);
			}
		}

		weapons.clear();

		weapon.equip(this);
		weapons.add(weapon);
		for (Condition condition : conditions) {
			condition.equip(this);
		}
		updateWeaponAttack();
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

	public void weaponReset() {
		for (Weapon weapon : weapons) {
			weapon.takeOff(this);
		}
		weapons.clear();
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器, ""));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーサブ武器, ""));
		weapons.add(CreateWeapon.INSTANCE.create(1));
		updateWeaponAttack();
	}

	public int getWeaponAttack() {
		int weaponAttack = 0;
		for (Weapon weapon : weapons) {
			weaponAttack += weapon.getFinalAttack();
		}
		return weaponAttack;
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

	public int countItem(int id) {
		int n = 0;
		for (int i = 0, l = items.size(); i < l; i++) {
			if (items.get(i).getId() == id) {
				n++;
			}
		}
		return n;
	}

	public int countItemName(String name) {
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

	public boolean setEXPGold(int exp, int gold) {
		this.gold += gold;
		this.sumExp += exp;
		this.exp -= exp;
		boolean levelFlag = false;
		if (this.exp <= 0) {
			levelUp();
			levelFlag = true;
		}
		setLv(lv);
		setHP(hp);
		setAttack(attack);
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー経験値, String.valueOf(this.exp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー合計経験値, String.valueOf(sumExp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーお金, String.valueOf(this.gold)));
		return levelFlag;
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

	public void plusTension(Battle battle, List<ActionInfo> infos, ActionInfo nowInfo) {
		int up = nowInfo.getFinalNumber();
		int t = this.tension + up;
		if (t > 100) {
			t = 100;
		}
		nowInfo.addMessages("気合が" + t + "になった");
		setTension(t);
		if (0 < up) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンションアップ, String.valueOf(up)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーテンションダウン, String.valueOf(up)));
		}
		// return "気合が" + this.tension + "になった。";
	}

	public void resetTension(Battle battle) {
		if (amountCondition(ConditionEnum.気合ビート) != 1) {
			setTension(0);
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーコンディションメッセージ, "気合ビート"));
		}
	}

	public void plusMultiBoost(double multiBoost) {
		this.multiBoost *= multiBoost;
	}

	public void plusPlusBoost(int plusBoost) {
		this.plusBoost += plusBoost;
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

	public void plusMaxItem(int plus) {
		maxItem += plus;
		if (20 < maxItem) {
			itemSwtchFlag = true;
			Battle.addLogs(new ScreenChange(ScreenEnum.アイテム切り替えボタン表示, ""));
		}
		showItem();
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
		return priceMulti * debugPrice;
	}

	public void plusPriceMulti(double priceMulti) {
		this.priceMulti *= priceMulti;
	}

	public void setPriceMulti(double priceMulti) {
		this.priceMulti = priceMulti;
	}

	public double getPriceMultiItem() {
		return priceMultiItem;
	}

	public void setPriceMultiItem(double priceMultiItem) {
		this.priceMultiItem = priceMultiItem;
	}

	public void resetDebugPrice() {
		debugPrice = 1;
	}

	public void zeroDebugPrice() {
		debugPrice = 0;
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

	// public boolean getItemSwtchFlag() {
	// return itemSwtchFlag;
	// }

	// public void setItemSwtchFlag(boolean itemSwtchFlag) {
	// this.itemSwtchFlag = itemSwtchFlag;
	// }

}

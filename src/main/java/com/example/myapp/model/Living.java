package com.example.myapp.model;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateCondition;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.weapons.Weapon;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class Living {
	protected String name;
	protected int hp;
	protected int maxHp;
	protected int attack;
	protected int exp;
	protected int gold;
	protected List<Condition> conditions;

	public Living() {
		conditions = new ArrayList<>();
	}

	public abstract void setDamage(int damage);

	public abstract void setHeal(int heal);

	public abstract void death(Battle battle, List<ActionInfo> infos, int n);

	public void calcDamage(Battle battle, List<ActionInfo> infos, int n) {
		for (Condition condition : conditions) {
			condition.calcDamage(battle, this, infos, n);
		}
		//int resultDamage = simulateDamage(battle, infos,n);
		//String text = name + "に" + resultDamage + "ダメージを与えた。";
		int damage = infos.get(n).getDamage();
		setDamage(damage);
		infos.get(n).addMessages(name + "は" + damage + "ダメージを受けた");
		for (Condition condition : conditions) {
			condition.setDamage(battle, this, infos, n);
		}
		if (hp <= 0) {
			death(battle, infos, n);
		}
	}

	public void calcHeal(Battle battle, List<ActionInfo> infos, int n) {
		for (Condition condition : conditions) {
			condition.calcHeal(battle, this, infos, n);
		}
		//int resultDamage = simulateDamage(battle, infos,n);
		//String text = name + "に" + resultDamage + "ダメージを与えた。";
		setHeal(infos.get(n).getDamage());
	}

	/*public String calcDamage(Battle battle, ActionInfo info) {
		int damage = info.getDamage();
		if (action == 4 && !info.getPenetrate()) {
			damage = 0;
		}
		setDamage(damage);
		String text = damage + "を受けた。";
		return text;
	}*/
	public void plusCondition(Battle battle, List<ActionInfo> infos, int n, ConditionEnum ce) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(battle, this, infos, n);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce);
		condition.newCondition(battle, this, infos, n);
		conditions.add(condition);
	}

	public void plusCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(this);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce);
		condition.newCondition(this);
		conditions.add(condition);
	}

	public int amountCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				return condition.getAmount();
			}
		}
		return 0;
	}

	public void setConditionTurn(ConditionEnum ce, String turn) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.setTurn(turn);
			}
		}
	}

	public void removeCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (int i = 0, l = conditions.size(); i < l; i++) {
			if (conditions.get(i).getId() == id) {
				conditions.get(i).remove();
				return;
			}
		}
	}

	public int getHP() {
		return hp;
	}

	public abstract void setHP(int hp);

	public int getMAXHP() {
		return maxHp;
	}

	public void plusMAXHP(int maxHp) {
		setMAXHP(this.maxHp + maxHp);
	}

	public void setMAXHP(int maxHp) {
		this.maxHp = maxHp;
		if (this instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー最大HP, String.valueOf(maxHp)));
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー攻撃力, String.valueOf(attack)));
			int sum = 0;
			for (Weapon weapon : ((Player) this).getWeapons()) {
				sum += weapon.getAttack();
			}
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー武器攻撃力, String.valueOf(sum)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター攻撃力, String.valueOf(attack)));
		}
	}

	public int getEXP() {
		return exp;
	}

	public int getGold() {
		return gold;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void plusEXP(int exp) {
		setEXP(this.exp + exp);
	}

	public void setEXP(int exp) {
		this.exp = exp;
		if (this instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤー経験値, String.valueOf(exp)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター経験値, String.valueOf(exp)));
		}
	}

	public void plusGold(int gold) {
		setGold(this.gold + gold);
	}

	public void setGold(int gold) {
		this.gold = gold;
		if (this instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーお金, String.valueOf(gold)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスターお金, String.valueOf(gold)));
		}
	}

}
package com.example.myapp.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateCondition;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class Living {
	protected String name;
	protected int lv;
	protected int hp;
	protected int maxHp;
	protected int attack;
	protected double attackMulti;
	protected int attackPlus;
	protected int exp;
	protected int gold;
	protected List<Condition> conditions;
	private boolean death;

	public Living() {
		conditions = new ArrayList<>();
		attackMulti = 1;
	}

	public abstract void setDamage(int damage);

	public abstract void setHeal(int heal);

	public abstract void death(Battle battle, List<ActionInfo> infos);

	public void calcDamage(Battle battle, List<ActionInfo> infos, ActionInfo nowInfo) {
		for (Condition condition : conditions) {
			condition.calcDamagePlus(battle, this, infos, nowInfo);
		}

		for (Condition condition : conditions) {
			condition.calcDamageMulti(battle, this, infos, nowInfo);
		}

		if (Objects.nonNull(nowInfo.getAttacker())) {
			for (Condition condition : nowInfo.getAttacker().getConditions()) {
				condition.delayMode(battle, nowInfo.getAttacker(), infos, nowInfo);
			}
		}

		int damage = nowInfo.getFinalNumber();
		nowInfo.addMessages(name + "は" + damage + "ダメージを受けた", this);
		if (0 < damage) {
			setDamage(damage);
			if (this instanceof Player && nowInfo.getActionType() == ActionTypeEnum.モンスターの攻撃) {
				Monster monster = (Monster) (nowInfo.getAttacker());
				monster.getAction().bonusEffect(battle, monster, infos, nowInfo);
			}
			for (Condition condition : conditions) {
				condition.setDamage(battle, this, infos, nowInfo);
			}

			Iterator<Condition> iterator = conditions.iterator();
			while (iterator.hasNext()) {
				Condition condition = iterator.next();
				System.out.println(condition.getName());
				if (condition.isConditionEnd()) {
					condition.removeCondition(battle, this, infos);
					iterator.remove();
				}
			}

			// conditions.removeIf(condition -> condition.isConditionEnd());

			if (hp <= 0) {
				for (Condition condition : conditions) {
					condition.death(battle, this, infos);
				}
				if (0 < amountCondition(ConditionEnum.根性)) {
					removeCondition(ConditionEnum.根性);
				} else {
					death(battle, infos);
				}
			} else {
				if (Objects.nonNull(nowInfo.getAttacker())) {
					for (Condition condition : nowInfo.getAttacker().getConditions()) {
						condition.copySword(battle, nowInfo.getAttacker(), infos, nowInfo);
					}
				}
				for (Condition condition : conditions) {
					condition.counter(battle, this, infos, nowInfo);
				}
			}
		}
	}

	public void calcHeal(Battle battle, List<ActionInfo> infos, ActionInfo nowInfo) {
		for (Condition condition : conditions) {
			condition.calcHeal(battle, this, infos, nowInfo);
		}
		int heal = nowInfo.getFinalNumber();
		if (heal != 0) {
			setHeal(heal);
			nowInfo.addMessages(name + "は" + heal + "回復した", this);
			for (Condition condition : conditions) {
				condition.setHeal(battle, this, infos, nowInfo);
			}
		}
	}

	// ターン中
	public void plusCondition(Battle battle, List<ActionInfo> infos, ConditionEnum ce) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(battle, this, infos);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce);
		condition.newCondition(battle, this, infos);
		conditions.add(condition);
		ActionInfo info = new ActionInfo();
		info.addMessages(name + "は" + condition.getName() + "状態になった", this);
		infos.add(info);
	}

	// モンスター出現時
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

	// モンスター出現時、スタッツあり
	public void plusCondition(ConditionEnum ce, int[] status) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(this);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce, status);
		condition.newCondition(this);
		conditions.add(condition);
	}

	// バトル中、スタッツあり
	public void plusCondition(Battle battle, List<ActionInfo> infos, ConditionEnum ce, int[] status) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(this);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce, status);
		condition.newCondition(battle, this, infos);
		conditions.add(condition);
	}

	public void eternalCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(this);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce);
		condition.newCondition(this);
		condition.setNonDelete(true);
		condition.setInfinity();
		conditions.add(condition);
	}

	public void eternalCondition(ConditionEnum ce, int[] status) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.plusAmount(this);
				return;
			}
		}
		Condition condition = CreateCondition.INSTANCE.create(ce, status);
		condition.newCondition(this);
		condition.setNonDelete(true);
		condition.setInfinity();
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

	public Condition getCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (int i = 0, l = conditions.size(); i < l; i++) {
			if (conditions.get(i).getId() == id) {
				return conditions.get(i);
			}
		}
		return null;
	}

	public void setConditionTurn(ConditionEnum ce, int turn) {
		int id = ce.getId();
		for (Condition condition : conditions) {
			if (condition.getId() == id) {
				condition.setTurn(turn);
			}
		}
	}

	public void minusCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (int i = 0, l = conditions.size(); i < l; i++) {
			if (conditions.get(i).getId() == id) {
				conditions.get(i).minusCondition(this);
				return;
			}
		}
	}

	public void removeCondition(ConditionEnum ce) {
		int id = ce.getId();
		for (int i = 0, l = conditions.size(); i < l; i++) {
			if (conditions.get(i).getId() == id) {
				conditions.get(i).removeCondition(this);
				conditions.remove(i);
				return;
			}
		}
	}

	public void removeCondition(Battle battle, ConditionEnum ce) {
		int id = ce.getId();
		for (int i = 0, l = conditions.size(); i < l; i++) {
			if (conditions.get(i).getId() == id) {
				conditions.get(i).removeCondition(battle, this, null);
				conditions.remove(i);
				return;
			}
		}
	}

	public String getName() {
		return name;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
		if (this instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.プレイヤーLV, String.valueOf(lv)));
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
		if (maxHp < hp) {
			hp = maxHp;
		}

		if (999 < maxHp) {
			maxHp = 999;
		}

		if (this instanceof Player) {
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
	}

	public abstract int getFinalAttack();

	public abstract void updateFinalAttack();

	public void setAttack(int attack) {
		if (attack < 0) {
			attack = 0;
		}
		this.attack = attack;
		updateFinalAttack();
	}
	
	public void plusAttack(int attack) {
		setAttack(this.attack + attack);
	}
	
	public int getAttack() {
		return attack;
	}

	public void setAttackMulti(double attackMulti) {
		this.attackMulti = attackMulti;
		updateFinalAttack();
	}

	public void plusAttackMulti(double attackMulti) {
		setAttackMulti(this.attackMulti * attackMulti);
	}

	public void setAttackPlus(int attackPlus) {
		this.attackPlus = attackPlus;
		updateFinalAttack();
	}

	public void plusAttackPlus(int attackPlus) {
		setAttackPlus(this.attackPlus + attackPlus);
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
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター経験値, exp + "#" + ((Monster) this).getNumber()));
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
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスターお金, gold + "#" + ((Monster) this).getNumber()));
		}
	}

	public boolean isDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

}
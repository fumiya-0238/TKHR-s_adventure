package com.example.myapp.model.monsters;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.monsters.actions.AttackActionList;

public abstract class Monster extends Living {
	protected String name;
	protected int OverHP;
	protected int MAXOverHP;
	protected int EXP;
	protected int Gold;
	protected int Turn;
	protected int ID;
	protected boolean death;

	public void init() {
		MAXHP = HP;
		MAXOverHP = OverHP;
	}

	final public void calcDamageResult(Battle battle, ActionInfo info) {
		int resultDamage = simulateDamage(battle, info);
		String text = name + "に" + resultDamage + "ダメージを与えた。";
		setDamage(resultDamage);
	}

	final public int simulateDamage(Battle battle, ActionInfo info) {
		int action = battle.getPlayer().getAction();
		int damage = info.getDamage();
		if (amountCondition(CreateCondition.CONTROL_SWITCH) == 1 && action == 3) {
			battle.setMonster(new 暴走培養(34));
		}
		if (amountCondition(CreateCondition.WEEK_INVALID) == 1 && action == 2) {
			damage = 0;
		}

		return damage;
	}

	public abstract void actions(Battle battle, ActionInfo info);

	public String standardDeath(Battle battle) {
		// text.append(getName() + "を倒した。");
		if (amountCondition(CreateCondition.TENACITY) == 1)
			AttackActionList.INSTANCE.normalAttack(battle, new ActionInfo());
		battle.getPlayer().setEXPGold(EXP, Gold, Turn > 0, OverHP == 0);
		return "";
	}

	public void turnEnd() {
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

	public String getName() {
		return name;
	}

	public int getOverHP() {
		return OverHP;
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

}

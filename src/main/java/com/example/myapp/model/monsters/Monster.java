package com.example.myapp.model.monsters;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.actions.Action;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public abstract class Monster extends Living {
	protected int id;
	protected int overHp;
	protected int maxOverHp;
	protected int turn;
	protected List<Action> actions;
	protected List<String> relateds;
	protected Action action;
	protected int number;
	private int bonus;
	protected boolean turnFlag;

	public Monster() {
		actions = new ArrayList<>();
	}

	public abstract void reset();

	public abstract void actions(Battle battle);

	public void setStatus(String name, int hp, int overHp, int attack, int exp, int gold, int turn) {
		this.name = name;
		if (1 < lv) {
			hp = hp * lv * 2;
			overHp = overHp * lv * 2;
			attack = attack * lv;
			exp = exp * lv * 2;
			gold = gold * lv * 2;
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター名, name + "LV" + lv + "#" + number));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター名, name + "#" + number));
		}

		setHP(hp);
		maxHp = hp;
		setOverHP(overHp);
		maxOverHp = overHp;
		if (999 < maxOverHp) {
			maxOverHp = 999;
		}
		setAttack(attack);
		setEXP(exp);
		setGold(gold);
		setTurn(turn);
	}

	public void updateFinalAttack() {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター攻撃力, getFinalAttack() + "#" + getNumber()));
	}

	public int getFinalAttack() {
		int finalAttack = (int) ((attack + attackPlus) * attackMulti);
		if (999 < finalAttack) {
			finalAttack = 999;
		}
		return finalAttack;
	}

	@Override
	public void death(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		if (isDeath()) {
			return;
		}
		// nowInfo.processFlagIsTrue();
		setDeath(true);
		if (overHp < 0) {
			overHp = 0;
		}
		setHP(0);
		battle.messageBatch();

		for (Condition condition : conditions) {
			condition.hpZero(battle, this, infos);
		}
		battle.monsterLiveCheck();
		if (0 == amountCondition(ConditionEnum.執念) && 0 == amountCondition(ConditionEnum.強い執念)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター削除, String.valueOf(number)));
			if (!battle.monsterLiveIs()) {
				Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
			}
		}
		// Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		// battle.turnEnd(infos);
		// Battle.addLogs(new ScreenChange(ScreenEnum.モンスター撃破, name));
		// Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除,
		// String.valueOf(number)));
		if (!battle.monsterLiveIs()) {
			battle.turnEnd(infos);
		}
		battle.beatMonsterA(this);
		//battle.beatMonster(this);
		// battle.nextFloorCheck();
	}

	public void setTurn(int turn) {
		this.turn = turn;
		if (0 < turn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ボーナスターン, turn + "#" + number));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.ボーナスターン消滅, String.valueOf(number)));
		}
	}

	public void plusTurn(int turn) {
		if (turn == 0) {
			return;
		}
		setTurn(this.turn + turn);
	}

	@Override
	public void setHP(int hp) {
		this.hp = hp;
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターHP, hp + "#" + number));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターオーバーHP, overHp + "#" + number));
	}

	public void setOverHP(int overHp) {
		this.overHp = overHp;
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターオーバーHP, overHp + "#" + number));
	}

	@Override
	public void setDamage(int damage) {
		int n = hp - damage;
		int overN = overHp - damage;
		if (0 < n) {
			setHP(n);
			setOverHP(overN);
		} else {
			hp = n;
			overHp = overN;
		}
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ, damage + "#" + number));
	}

	@Override
	public void setHeal(int heal) {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター回復, heal + "#" + number));
		int n = hp + heal;
		overHp += heal;
		if (n > maxHp) {
			n = maxHp;
			overHp = maxOverHp;
		}
		setHP(n);
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getOverHP() {
		return overHp;
	}

	public int getTurn() {
		return turn;
	}

	public List<Action> getActions() {
		return actions;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void plusBonus() {
		bonus++;
	}

	public int getBonus() {
		return bonus;
	}

	public void resetBonus() {
		bonus = 0;
	}

	public boolean isTurnFlag() {
		return turnFlag;
	}

	public void setTurnFlag(boolean turnFlag) {
		this.turnFlag = turnFlag;
	}
}
package com.example.myapp.model.monsters;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.Living;
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
	private boolean death;

	public Monster() {
		actions = new ArrayList<>();
	}

	public abstract void reset();

	public abstract void actions(Battle battle);

	public void setStatus(int id, String name, int hp, int overHp, int attack, int exp, int gold, int turn) {
		this.id = id;
		this.name = name;
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター名, String.valueOf(name)));
		setHP(hp);
		maxHp = hp;
		setOverHP(overHp);
		maxOverHp = overHp;
		setAttack(attack);
		setEXP(exp);
		setGold(gold);
		setTurn(turn);
	}

	@Override
	public void death(Battle battle, List<ActionInfo> infos) {
		if (death) {
			return;
		}
		infos.get(0).processFlagIsTrue();
		death = true;
		// TODO 自動生成されたメソッド・スタブ
		if (overHp < 0) {
			overHp = 0;
		}
		setHP(0);
		battle.messageBatch();

		for (Condition condition : conditions) {
			condition.hpZero(battle, this, infos);
		}
		if (0 == amountCondition(ConditionEnum.執念) && 0 == amountCondition(ConditionEnum.強い執念)) {
			Battle.addLogs(new ScreenChange(ScreenEnum.モンスター削除, ""));
			Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
		}
		//Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		battle.turnEnd(infos);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター撃破, name));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除, ""));
		battle.getPlayer().setEXPGold(exp, gold, turn > 0, overHp == 0);
		battle.nextFloorCheck();

	}

	public void setTurn(int turn) {
		this.turn = turn;
		if (0 < turn) {
			Battle.addLogs(new ScreenChange(ScreenEnum.ボーナスターン, String.valueOf(turn)));
		} else {
			Battle.addLogs(new ScreenChange(ScreenEnum.ボーナスターン消滅, ""));
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
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターHP, String.valueOf(hp)));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターオーバーHP, String.valueOf(overHp)));
	}

	public void setOverHP(int overHp) {
		this.overHp = overHp;
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターオーバーHP, String.valueOf(overHp)));
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
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ, String.valueOf(damage)));
	}

	@Override
	public void setHeal(int heal) {
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター回復, String.valueOf(heal)));
		int n = hp + heal;
		overHp += heal;
		if (n > maxHp) {
			n = maxHp;
			overHp = maxOverHp;
		}
		setHP(n);
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

	public boolean deathIs() {
		return death;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}

package com.example.myapp.model.conditions;

import java.util.List;
import java.util.Objects;

import com.example.myapp.model.Living;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Condition {
	private int id;
	protected String name;
	protected String turn;
	protected int amount;
	protected boolean duplication;

	public void setStatus(int id, String name, boolean duplication) {
		this.id = id;
		this.name = name;
		this.duplication = duplication;
	}

	//バトル中コンディション発現
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos, int n) {
		setTurn("1");
		amount = 1;
	}
	
	//最初コンディション発現
	public void newCondition(Living living) {
		setTurn("1");
		amount = 1;
	}

	//攻撃力補正
	public void attack(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}
	//攻撃力補正
	public void criticalAttack(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}
	//ダメージ補正
	public void damagePlus(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}

	//受ける側ダメージ補正
	public void calcDamage(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}

	//ダメージを受けた後
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}
	//行動後
	public void afterAction(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}
	//回復時
	public void calcHeal(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}

	//アイテム使用時
	public void useItem(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}

	//バトル開始時
	public void battleStart(Battle battle, Living living, List<ActionInfo> infos) {
	}

	//ターン開始時
	public void turnStart(Battle battle, Living living, List<ActionInfo> infos) {
	}

	//モンスター行動時
	public void monsterAction(Battle battle) {
	}

	//ゴールド消費時
	public void useGold(int gold) {
	}

	//ターン終了時
	public void turnEnd(Battle battle, Living living, List<ActionInfo> infos) {
		if (turn.equals("∞")) {
			return;
		}
		int turn = Integer.parseInt(this.turn);
		if (turn > 0) {
			turn--;
			this.turn = String.valueOf(turn);
			if (turn == 0) {
				amount = 0;
			}
		}
	}

	//死亡時
	public void death(Battle battle, Living living, List<ActionInfo> infos, int n) {
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public String getTurn() {
		return turn;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void plusAmount(Battle battle, Living living, List<ActionInfo> infos, int n) {
		if (amount == 0) {
			newCondition(battle, living, infos, n);
		}
		if (duplication) {
			amount++;
		}
	}

	public void plusAmount(Living living) {
		if (amount == 0) {
			newCondition(living);
		}
		if (duplication) {
			amount++;
		}
	}

	public void setInfinity() {
		turn = "∞";
	}

	public void setTurn(String turn) {
		if (Objects.isNull(this.turn)) {
			this.turn = turn;
			return;
		}
		if (this.turn.equals("∞")) {
			return;
		}
		//if (!duplication && amount == 0) {
		this.turn = turn;
	}

	public void remove() {
		amount = 0;
		turn = "0";
	}

}

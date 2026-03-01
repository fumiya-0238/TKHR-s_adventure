
package com.example.myapp.model.conditions;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public abstract class Condition {
	private int id;
	protected String name;
	protected int turn;
	protected int amount;
	protected boolean duplication;
	protected boolean nonDelete;
	protected int lv;
	protected boolean conditionEnd;

	public void setStatus(int id, String name, boolean duplication) {
		this.id = id;
		this.name = name;
		this.duplication = duplication;
	}

	public void setLevel(int[] status) {
		lv = status[0];
		name = name.replace("<n>", String.valueOf(lv));
		for (int i = 1, l = status.length; i < l; i++) {
			int x = status[i];
			switch (i) {
			case 1:
				turn = x;
				break;
			case 2:
				amount = x;
				break;
			case 3:
				if (x == 1) {
					nonDelete = true;
				}
				break;
			}
		}
	}

	// バトル中コンディション発現
	/*
	 * 毒、防御、デスマッチ、スライム状態
	 */
	public void newCondition(Battle battle, Living living, List<ActionInfo> infos) {
		newCondition();
	}
	// 最初コンディション発現

	public void newCondition(Living living) {
		newCondition();
	}

	public void newCondition() {
		if (turn < 0) {
			turn = 0;
			return;
		}
		if (0 < turn) {
			return;
		}
		setTurn(1);
		amount = 1;
	}

	// バトル中コンディション削除
	/*
	 * 死神の呪い,スライム状態、毒
	 */
	public void removeCondition(Battle battle, Living living, List<ActionInfo> infos) {
	}

	public void removeCondition(Living living) {
	}

	// 攻撃力補正
	/**
	 * バーサーク、バトルゴング、アサルトぺネトレイト、貫通、擬態の術
	 */
	public void attack(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 攻撃力補正
	/*
	 * 強攻撃強化
	 */
	public void criticalAttack(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 防御
	/*
	 * ディレイモード
	 */
	public void defence(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// ためる時
	/*
	 * ためる強化、
	 */
	public void tension(Battle battle, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// ダメージ補正
	/**
	 * 小さな勇気
	 */
	public void damagePlus(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 受ける側ダメージ補正
	/*
	 * ダークアーマー
	 * 
	 */
	public void calcDamagePlus(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	/*
	 * 防御、バリア、ネペントの消化液、手加減無効、強攻撃無効、ランタンフレイム、眼チャージ、纏った草
	 */
	public void calcDamageMulti(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// ダメージを受けた後
	/*
	 * ゴールドチャンス、ダメージ共有、暴走スイッチ、纏った草、気まぐれ、アサルトブースター、ディレイモード
	 */
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	/*
	 * カウンター
	 */
	public void counter(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	/*
	 * コピーソード
	 */
	public void copySword(Battle battle, Living attacker, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// ディレイモード
	public void delayMode(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 回復量補正
	/*
	 * 回復封じ
	 */
	public void calcHeal(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 回復時
	/*
	 * ヒールチャージ、
	 */
	public void setHeal(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// 吸収時
	public void drain(Battle battle, Living living, List<ActionInfo> infos, ActionInfo nowInfo) {
	}

	// アイテム使用時
	/*
	 * アイテムヒール
	 */
	public void useItem(Battle battle, Living living, List<ActionInfo> infos) {
	}

	// バトル開始時
	/*
	 * 先制攻撃
	 */
	public void battleStart(Battle battle, Living living, List<ActionInfo> infos) {
	}

	// ターン開始時
	/*
	 * 
	 * */
	public void turnStart(Battle battle, Living living, List<ActionInfo> infos) {
	}

	// モンスター行動時
	/*
	 * 挑発、偶数攻撃
	 */
	public void monsterAction(Battle battle, Monster moonster) {
	}

	// ゴールド消費時
	/*
	 * VIP状態
	 */
	public void useGold(int gold) {
	}
	
	public void equip(Player player) {
	}

	// ターン終了時
	/*
	 * 死神の呪い、トゲトゲ、アサルトブースター
	 */
	public void turnEnd(Battle battle, Living living, List<ActionInfo> infos) {
		if (turn == -1) {
			return;
		}
		turn--;
		if (turn == 0) {
			conditionEnd = true;
			removeCondition(battle, living, infos);
		}
	}

	// 死亡時
	/*
	 * 根性
	 */
	public void death(Battle battle, Living living, List<ActionInfo> infos) {
	}

	// 死亡時
	/*
	 * 執念、強い執念、おすそ分け
	 */
	public void hpZero(Battle battle, Living living, List<ActionInfo> infos) {
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

	public int getTurn() {
		return turn;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setNonDelete(boolean nonDelete) {
		this.nonDelete = nonDelete;

	}

	public boolean nonDeleteIs() {
		return nonDelete;

	}

	public boolean duplicationIs() {
		return duplication;
	}

	public void plusAmount(Battle battle, Living living, List<ActionInfo> infos) {
		amount++;
	}

	public void plusAmount(Battle battle, Living living) {
		amount++;
	}

	public void minusCondition(Living living) {
		amount--;
		if (amount == 0) {
			living.getConditions().remove(this);
		}
	}

	public void plusAmount(Living living) {
		if (!duplication) {
			return;
		}
		amount++;
	}

	public void plusAmount(int amount) {
		this.amount += amount;
	}

	public void setInfinity() {
		turn = -1;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getLv() {
		return lv;
	}

	public boolean isConditionEnd() {
		return conditionEnd;
	}

}

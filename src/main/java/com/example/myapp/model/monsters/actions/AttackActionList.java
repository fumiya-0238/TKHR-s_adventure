package com.example.myapp.model.monsters.actions;

import java.util.Objects;

import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.model.items.ダークマター;
import com.example.myapp.model.monsters.Monster;

public enum AttackActionList {
	INSTANCE();

	private void commonAction(Battle battle, ActionInfo info, String actionName) {
		Player player = battle.getPlayer();
		/*
		 * String text = battle.getMonster().getName() + actionName + "。"
		 * + player.calcDamageResult(damage,_ penetrate, battle);
		 */
	}

	public void normalAttack(Battle battle, ActionInfo info) {
		String actionName = "の攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, info, actionName);
	}

	public void criticalAttack(Battle battle, ActionInfo info) {
		String actionName = "の強攻撃";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, info, actionName);
	}

	public void laserAttack(Battle battle, ActionInfo info) {
		String actionName = "はレーザーを撃ってきた";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, info, actionName);
	}

	public void runawayLaserAttack(Battle battle, ActionInfo info) {
		String actionName = "は暴走レーザーを撃ってきた";
		int damage = (int) (battle.getMonster().getATK() * 3);
		commonAction(battle, info, actionName);
	}

	public void drainAttack(Battle battle, ActionInfo info) {
		String actionName = "の吸収攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, info, actionName);
		if (damage != 0) {
			battle.getMonster().setHeal(damage / 2);
		}
	}

	public void panetrateAttack(Battle battle, ActionInfo info) {
		String actionName = "の貫通攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, info, actionName);
	}

	public void panetrateCriticalAttack(Battle battle, ActionInfo info) {
		String actionName = "の強貫通攻撃";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, info, actionName);
	}

	public void pecking(Battle battle, ActionInfo info) {
		String actionName = "のついばむ";
		Monster monster = battle.getMonster();
		int damage = monster.getATK();
		commonAction(battle, info, actionName);
		if (damage != 0) {
			Item item = battle.getPlayer().descSearchItem("草");
			if (Objects.nonNull(item)) {
				if (item.getName().equals("薬草")) {
					monster.setHeal(10);
				}
				if (item.getName().equals("アロエ草")) {
					monster.setHeal(20);
				} else {
					monster.calcDamageResult(battle, new ActionInfo(true, 10, true));
				}
			}
		}
	}

	public void darkMatterSlash(Battle battle, ActionInfo info) {
		String actionName = "はダークマタースラッシュを放った";
		int damage = battle.getMonster().getATK();
		commonAction(battle, info, actionName);
		Player player = battle.getPlayer();
		if (damage != 0) {
			for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l; j++)
				battle.getPlayer().setItem(new ダークマター());
		}
	}

	public void matterDrain(Battle battle, ActionInfo info) {
		String actionName = "はマタードレインを使った";
		int damage = battle.getMonster().getATK();
		commonAction(battle, info, actionName);
		Player player = battle.getPlayer();
		if (damage != 0) {
			battle.getMonster().setHeal(player.countItem("ダークマター"));
		}
	}
}

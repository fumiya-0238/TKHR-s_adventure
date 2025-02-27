package com.example.myapp.model.monsters.actions;

import java.util.Objects;

import com.example.myapp.service.GameService;
import tkhr.Player;
import tkhr.battleScreen.messagePanel.MessagePanel;
import tkhr.items.Item;
import tkhr.items.ダークマター;
import tkhr.monsters.Monster;

public enum AttackActionList {
	INSTANCE();

	private void commonAction(GameService battle, String actionName, int damage,
			boolean penetrate) {
		Player player = battle.getPlayer();
		String text = battle.getMonster().getName() + actionName + "。"
				+ player.calcDamageResult(damage, penetrate, battle);
	}

	public void normalAttack(Monster monster) {
		String actionName = "の攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, actionName, damage, false);
	}

	public void criticalAttack(GameService battle) {
		String actionName = "の強攻撃";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, messagePanel, actionName, damage, false);
	}

	public void laserAttack(GameService battle) {
		String actionName = "はレーザーを撃ってきた";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, messagePanel, actionName, damage, false);
	}

	public void drainAttack(GameService battle) {
		String actionName = "の吸収攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, messagePanel, actionName, damage, false);
		if (damage != 0) {
			battle.getMonster().setHeal(damage / 2);
		}
	}

	public void panetrateAttack(GameService battle, MessagePanel messagePanel) {
		String actionName = "の貫通攻撃";
		int damage = battle.getMonster().getATK();
		commonAction(battle, messagePanel, actionName, damage, true);
	}

	public void panetrateCriticalAttack(GameService battle, MessagePanel messagePanel) {
		String actionName = "の強貫通攻撃";
		int damage = (int) (battle.getMonster().getATK() * 1.5);
		commonAction(battle, messagePanel, actionName, damage, true);
	}

	public void pecking(GameService battle, MessagePanel messagePanel) {
		String actionName = "のついばむ";
		Monster monster = battle.getMonster();
		int damage = monster.getATK();
		commonAction(battle, messagePanel, actionName, damage, false);
		if (damage != 0) {
			Item item = battle.getPlayer().descSearchItem("草");
			if (Objects.nonNull(item)) {
				if (item.getName().equals("薬草")) {
					monster.setHeal(10);
				}
				if (item.getName().equals("アロエ草")) {
					monster.setHeal(20);
				} else {
					monster.calcDamageResult(10, true, battle);
				}
			}
		}
	}

	public void darkMatterSlash(GameService battle, MessagePanel messagePanel) {
		String actionName = "はダークマタースラッシュを放った";
		int damage = battle.getMonster().getATK();
		commonAction(battle, messagePanel, actionName, damage, false);
		Player player = battle.getPlayer();
		if (damage != 0) {
			for (int j = 0, l = player.getMaxItem() - player.getItems().size(); j <= l; j++)
				battle.setItem(new ダークマター());
		}
	}

	public void matterDrain(GameService battle, MessagePanel messagePanel) {
		String actionName = "はマタードレインを使った";
		int damage = battle.getMonster().getATK();
		commonAction(battle, messagePanel, actionName, damage, false);
		Player player = battle.getPlayer();
		if (damage != 0) {
			battle.getMonster().setHeal(player.countItem("ダークマター"));
		}
	}
}

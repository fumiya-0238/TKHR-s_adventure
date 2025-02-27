package com.example.myapp.model.monsters.actions;

import tkhr.Battle;
import tkhr.battleScreen.messagePanel.MessagePanel;
import tkhr.monsters.Monster;

public class AssistActionList {
	public static void heal(GameService battle,MessagePanel messagePanel,int heal) {
		Monster monster=battle.getMonster();
		monster.setHeal(heal);
		messagePanel.addMessage(monster.getName()+"の攻撃。"+heal+"回復した。");
	}
}

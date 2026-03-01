package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class A31消化液 extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		int[] ltad = { 1, 4 };
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.被ダメージ2倍, ltad);
	}
}

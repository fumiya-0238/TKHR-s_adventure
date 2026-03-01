package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class A15デスマッチ extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		monster.plusCondition(battle, infos, ConditionEnum.攻撃力2倍);
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.攻撃力2倍);
		monster.plusCondition(battle, infos, ConditionEnum.トゲトゲ);
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.トゲトゲ);
	}
}

package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class デスマッチ extends Action{
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		battle.getMonster().plusCondition(battle, infos, ConditionEnum.攻撃力2倍);
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.攻撃力2倍);
		battle.getMonster().plusCondition(battle, infos, ConditionEnum.トゲトゲ);
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.トゲトゲ);
	}
}

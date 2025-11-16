package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.actions.Action;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 先制攻撃 extends Condition{
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	
	@Override
	public void battleStart(Battle battle, Living living, List<ActionInfo> infos) {
		//Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
		//infos.remove(0);
		//battle.commonAction(infos);
		//Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除, ""));
		battle.conditionMessage(living, name);
		Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		Action action = CreateAction.INSTANCE.create(1);
		battle.getMonster().setAction(action);
		action.actionEffect(battle, infos);
		Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		//Battle.addLogs(new ScreenChange(ScreenEnum.モンスター削除, ""));
		//betweenTurn
	}
}

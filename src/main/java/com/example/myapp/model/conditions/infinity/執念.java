package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.actions.Action;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 執念 extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
		amount = 1;
	}

	@Override
	public void hpZero(Battle battle, Living living, List<ActionInfo> infos) {
		Monster monster = (Monster) living;
		String number = String.valueOf(monster.getNumber());
		Battle.addLogs(new ScreenChange(ScreenEnum.ストップ, ""));
		battle.commonAction(infos);
		ActionInfo info = new ActionInfo();
		info.addMessages("状態発動:<" + name + ">", living);
		infos.add(info);
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスターダメージ削除, number));
		battle.conditionMessage(living, name);
		Action action = CreateAction.INSTANCE.create(1);
		monster.setAction(action);
		action.actionEffect(battle, monster, infos);
		Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター削除, number));
	}
}
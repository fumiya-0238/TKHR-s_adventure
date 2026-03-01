package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class ツララボディ extends Condition {
	@Override
	public void newCondition() {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}
	
	@Override
	public void counter(Battle battle, Living receiver, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (nowInfo.getActionType() == ActionTypeEnum.ダメージ || nowInfo.getActionType() == ActionTypeEnum.カウンター
				|| nowInfo.getActionType() == ActionTypeEnum.火の玉) {
			return;
		}
		int percent = receiver.getHP() * 100 / receiver.getMAXHP();
		if (percent <= 50) {
			Battle.addLogs(new ScreenChange(ScreenEnum.待機, "300"));
			nowInfo.addMessages("状態発動:<" + name + ">", receiver);
			battle.conditionMessage(receiver, name);
			CreateAction.INSTANCE.create(38).actionEffect(battle, (Monster) receiver, infos);
		}
	}
}
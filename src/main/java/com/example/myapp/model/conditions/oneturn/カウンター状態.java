package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class カウンター状態 extends Condition {
	@Override
	public void counter(Battle battle, Living receiver, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (nowInfo.getActionType() == ActionTypeEnum.ダメージ || nowInfo.getActionType() == ActionTypeEnum.カウンター
				|| nowInfo.getActionType() == ActionTypeEnum.火の玉) {
			return;
		}
		nowInfo.addMessages("状態発動:<" + name + ">", receiver);
		battle.conditionMessage(receiver, name);
		if (receiver instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.カウンター攻撃, ""));
			((Player) receiver).attack(battle, infos, ActionTypeEnum.カウンター, nowInfo.getAttacker());
		} else {
			CreateAction.INSTANCE.create(38).actionEffect(battle, (Monster) receiver, infos);
		}
	}
}

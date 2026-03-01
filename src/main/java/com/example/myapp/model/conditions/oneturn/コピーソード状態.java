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

public class コピーソード状態 extends Condition {
	@Override
	public void copySword(Battle battle, Living attacker, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (nowInfo.getActionType() == ActionTypeEnum.ダメージ || nowInfo.getActionType() == ActionTypeEnum.カウンター
				|| nowInfo.getActionType() == ActionTypeEnum.火の玉) {
			return;
		}
		nowInfo.addMessages("状態発動:<" + name + ">", attacker);
		battle.conditionMessage(attacker, name);
		if (attacker instanceof Player) {
			Battle.addLogs(new ScreenChange(ScreenEnum.カウンター攻撃, ""));
			((Player) attacker).attack(battle, infos, ActionTypeEnum.カウンター, nowInfo.getReceiver());
		} else {
			CreateAction.INSTANCE.create(38).actionEffect(battle, (Monster) attacker, infos);
		}
	}
}

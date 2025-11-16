package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class 擬態の術 extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		Monster monster = battle.getMonster();
		String message = monster.getName() + "は" + name + "をした";
		Battle.addLogs(new ScreenChange(ScreenEnum.モンスター削除, ""));
		//Battle.addLogs(new ScreenChange(ScreenEnum.モンスター隠れ, ""));
		battle.getMonster().plusCondition(battle, infos, ConditionEnum.擬態の術);
		//String message = battle.getMonster().getName()+"の"+name;
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
	}

}

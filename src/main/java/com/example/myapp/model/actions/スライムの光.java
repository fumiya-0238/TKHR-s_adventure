package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class スライムの光 extends Action{
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		String message = battle.getMonster().getName() + "は" + name + "をした";
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		battle.getPlayer().plusCondition(battle, infos, ConditionEnum.スライム状態);
	}
}

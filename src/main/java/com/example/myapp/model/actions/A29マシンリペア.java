package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class A29マシンリペア extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		ActionInfo info = new ActionInfo();
		String message = monster.getName() + "の" + name;
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
		info.setReceiver(monster);
		info.setActionType(ActionTypeEnum.回復);
		info.setNumber(15);
		infos.add(info);
		monster.plusTurn(10);
	}

}

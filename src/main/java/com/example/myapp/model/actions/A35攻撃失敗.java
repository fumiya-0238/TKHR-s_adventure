package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class A35攻撃失敗 extends Action {
	@Override
	public void doAction(Battle battle, Monster monster,List<ActionInfo> infos) {
		ActionInfo info = new ActionInfo();
		infos.add(info);
		String message = monster.getName()+"の攻撃は失敗した";
		info.addMessages(message);
		Battle.addLogs(new ScreenChange(ScreenEnum.ウィンドウメッセージ, message));
	}
}

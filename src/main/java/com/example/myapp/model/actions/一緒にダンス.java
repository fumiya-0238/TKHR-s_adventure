package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 一緒にダンス extends Action {
	@Override
	public void doAction(Battle battle, List<ActionInfo> infos) {
		battle.useItem(0);
	}
}

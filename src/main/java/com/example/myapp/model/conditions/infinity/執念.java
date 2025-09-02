package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 執念 extends Condition {
	@Override
	public void newCondition(Living living) {
		// TODO 自動生成されたメソッド・スタブ
		setInfinity();
	}

	@Override
	public void death(Battle battle, Living living, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		infos.add(new ActionInfo());
		n++;
		CreateAction.INSTANCE.create(1).doAction(battle, infos, n);
	}
}
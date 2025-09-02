package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 気合いサブスク extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		active = !active;
	}

	public void registration(Battle battle) {
		super.registration(battle, ConditionEnum.気合サブスク);
	}

	public void cancel(Battle battle) {
		super.cancel(battle, ConditionEnum.気合サブスク);
	}
}

package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class I27トゲトゲゾーン extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getTargetMonster().plusCondition(ConditionEnum.トゲトゲ);
	}

}

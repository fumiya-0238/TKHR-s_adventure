package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ゴールドチャンス extends Item {

	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().plusCondition(battle,infos,n,ConditionEnum.ゴールドチャレンジ);
	}
}

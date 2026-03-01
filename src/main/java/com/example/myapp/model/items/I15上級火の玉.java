package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I15上級火の玉 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.火の玉, battle.getTargetMonster(), 8, true, infos);
	}
}

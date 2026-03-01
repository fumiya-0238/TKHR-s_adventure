package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I09ダメージ草 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, battle.getPlayer(), 10, true, infos);
	}
}

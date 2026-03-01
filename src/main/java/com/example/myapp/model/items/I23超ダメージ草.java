package com.example.myapp.model.items;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I23超ダメージ草 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, battle.getPlayer(), 30, true, infos);
		ActionInfo info = new ActionInfo();
		info.setNumber(50);
		info.setActionType(ActionTypeEnum.テンション);
		infos2.add(info);
		battle.getPlayer().plusCritical(1);
		infos.addAll(infos2);
	}
}

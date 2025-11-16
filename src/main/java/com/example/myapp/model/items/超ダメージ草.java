package com.example.myapp.model.items;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 超ダメージ草 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(30);
		info.setReceiver(battle.getPlayer());
		info.setActionType(1);
		infos2.add(info);

		ActionInfo info2 = new ActionInfo();
		info2.setNumber(50);
		info2.setActionType(3);
		infos2.add(info2);
		
		battle.getPlayer().plusCritical(1);

		infos.addAll(infos2);
	}
}

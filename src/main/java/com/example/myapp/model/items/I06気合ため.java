package com.example.myapp.model.items;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I06気合ため extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(25);
		info.setActionType(ActionTypeEnum.テンション);
		infos2.add(info);
		//battle.getPlayer().plusTension(battle, infos);
		infos.addAll(infos2);
	}
}

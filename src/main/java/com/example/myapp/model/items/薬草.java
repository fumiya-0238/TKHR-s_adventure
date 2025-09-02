package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 薬草 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		ActionInfo info = new ActionInfo();
		commonUse(info);
		info.setDamage(10);
		infos.add(info);
		battle.getPlayer().calcHeal(battle, infos, n);
	}
}
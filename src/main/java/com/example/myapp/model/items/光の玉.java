package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 光の玉 extends Item{
	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().plusCritical(1);
		infos.get(n).setDamage(7);
		battle.getMonster().calcDamage(battle,infos,n);
	}
}

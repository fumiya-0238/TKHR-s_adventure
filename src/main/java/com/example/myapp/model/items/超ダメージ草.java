package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 超ダメージ草 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		infos.get(n).setDamage(20);
		battle.getPlayer().plusTension(battle, infos, n, 50);
		battle.getPlayer().calcDamage(battle, infos, n);
	}
}

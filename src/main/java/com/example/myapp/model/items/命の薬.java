package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 命の薬 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> info) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getPlayer().plusMAXHP(20);
	}
}
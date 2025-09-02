package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class タイムアッパー extends Item{

	@Override
	public void use(Battle battle, List<ActionInfo> infos, int n) {
		// TODO 自動生成されたメソッド・スタブ
		battle.getMonster().plusTurn(1);
	}

}

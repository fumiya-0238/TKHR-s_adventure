package com.example.myapp.model.items;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アロエ草 extends Item{

	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(30);
		infos2.add(info);
		battle.getPlayer().calcHeal(battle, infos2);
		infos.addAll(infos2);
	}

}

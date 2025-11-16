package com.example.myapp.model.conditions.oneturn;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ダメージ共有状態 extends Condition {
	@Override
	public void setDamage(Battle battle, Living living, List<ActionInfo> infos) {
		List<ActionInfo> infos2 = new ArrayList<>();
		ActionInfo info = new ActionInfo();
		info.setNumber(infos.get(0).getNumber());
		info.setActionType(1);
		info.setPenetrate(true);
		infos2.add(info);
		if (living instanceof Player) {
			info.setReceiver(battle.getMonster());
			//battle.getMonster().calcDamage(battle, infos2);
		} else {
			info.setReceiver(battle.getPlayer());
			//battle.getPlayer().calcDamage(battle, infos2);
		}
		battle.conditionMessage(living, name);
		infos.addAll(infos2);
	}
}

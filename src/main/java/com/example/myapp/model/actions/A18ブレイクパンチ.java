package com.example.myapp.model.actions;

import java.util.List;

import com.example.myapp.creater.CreateWeapon;
import com.example.myapp.model.monsters.Monster;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.repository.ScreenChange;
import com.example.myapp.repository.ScreenEnum;

public class A18ブレイクパンチ extends Action {
	@Override
	public void doAction(Battle battle, Monster monster, List<ActionInfo> infos) {
		String message = monster.getName() + "は" + name + "をした";
		battle.getCommonEffect().commonMonsterAttack(battle, monster, monster.getFinalAttack(), false, message, infos);
	}

	@Override
	public void bonusEffect(Battle battle, Monster monster, List<ActionInfo> infos, ActionInfo nowInfo) {
		if (battle.getPlayer().getWeapons().get(0).getId() != 1) {
			battle.getPlayer().equip(CreateWeapon.INSTANCE.create(1));
			Battle.addLogs(new ScreenChange(ScreenEnum.武器破壊, ""));
		}
	}
}
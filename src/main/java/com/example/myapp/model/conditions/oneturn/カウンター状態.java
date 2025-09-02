package com.example.myapp.model.conditions.oneturn;

import java.util.List;

import com.example.myapp.creater.CreateAction;
import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class カウンター状態 extends Condition {
	@Override
	public void afterAction(Battle battle, Living living, List<ActionInfo> infos, int n) {
		if(amount==0) {
			return;
		}
		if(living.getHP()<=0) {
			return;
		}
		if (living instanceof Player) {
			((Player) living).attack(battle, infos, n);
		}else {
			CreateAction.INSTANCE.create(1).doAction(battle, infos, n);
			//((Monster) living)..doAction.(battle, info);
		}
	}
}

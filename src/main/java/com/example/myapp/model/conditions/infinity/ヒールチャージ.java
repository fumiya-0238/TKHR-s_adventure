package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class ヒールチャージ extends Condition {
	
	@Override
	public void newCondition(Living living) {
		setInfinity();
		amount = 1;
	}
	
	@Override
	public void calcHeal(Battle battle, Living living, List<ActionInfo> infos, int n) {
		((Player) living).plusTension(battle, infos, n, 25);
	}

}

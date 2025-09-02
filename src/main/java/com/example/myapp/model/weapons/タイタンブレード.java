package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class タイタンブレード extends Weapon {
	@Override
	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.defence(battle, infos, n);
		super.criticalAttack(battle, infos, n);
	}
}

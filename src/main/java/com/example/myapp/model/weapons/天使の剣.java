package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 天使の剣 extends Weapon {
	private void angelHeal(Battle battle, List<ActionInfo> infos, int n) {
		Player player = battle.getPlayer();
		infos.add(new ActionInfo());
		int size = infos.size() - 1;
		infos.get(size).setDamage(player.getMAXHP() / 10);
		player.calcHeal(battle, infos, size);
	}

	@Override
	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		super.attack(battle, infos, n);
		angelHeal(battle, infos, n);
	}

	@Override
	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.weekAttack(battle, infos, n);
		angelHeal(battle, infos, n);
	}

	@Override
	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.criticalAttack(battle, infos, n);
		angelHeal(battle, infos, n);
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		super.defence(battle, infos, n);
		angelHeal(battle, infos, n);
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		super.tension(battle, infos, n);
		angelHeal(battle, infos, n);
	}
}

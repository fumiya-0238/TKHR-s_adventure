package com.example.myapp.model.weapons;

import com.example.myapp.service.GameService;
import com.example.myapp.model.Player;
import com.example.myapp.model.conditions.CreateCondition;
import com.example.myapp.model.items.薬草;

public class 世界樹の剣 extends Weapon {
	public 世界樹の剣() {
		super("世界樹の剣", 100, "ターン終了時、薬草を1個手に入れる。強攻撃をするとアイテム欄の薬草を全て消滅し、消滅した数×5ダメージ追加", 7);

	}

	@Override
	public void attack(GameService battle) {
		super.attack(battle);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void weekAttack(GameService battle) {
		super.weekAttack(battle);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void criticalAttack(GameService battle) {
		Player player = battle.getPlayer();
		battle.getMonster().calcDamageResult((int) (player.getATK() * 1.5) + battle.getPlayer().countItem("薬草") * 5,
				(player.amountCondition(CreateCondition.PENETRATE) > 0), battle);
		/*
		 * public void removeItem(int i) {
		 * player.removeItem(i);
		 * notifyObservers();
		 * }
		 */
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void defence(GameService battle) {
		super.defence(battle);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void tension(GameService battle) {
		super.tension(battle);
		battle.getPlayer().setItem(new 薬草());
	}
}

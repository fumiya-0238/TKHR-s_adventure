package com.example.myapp.model.weapons;

import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;
import com.example.myapp.model.items.薬草;

public class 世界樹の剣 extends Weapon {
	public 世界樹の剣() {
		super("世界樹の剣", 100, "ターン終了時、薬草を1個手に入れる。強攻撃をするとアイテム欄の薬草を全て消滅し、消滅した数×5ダメージ追加", 7);

	}

	@Override
	public void attack(Battle battle, ActionInfo info) {
		super.attack(battle, info);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void weekAttack(Battle battle, ActionInfo info) {
		super.weekAttack(battle, info);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void criticalAttack(Battle battle, ActionInfo info) {
		super.criticalAttack(battle, info);
		Player player = battle.getPlayer();
		int n = 0;
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			if (item.getName().equals("薬草")) {
				player.getItems().remove(i);
				i--;
				n++;
			}
		}
		info.setDamage((int) (player.getATK() * 1.5 + n * 5));
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void defence(Battle battle, ActionInfo info) {
		super.defence(battle, info);
		battle.getPlayer().setItem(new 薬草());
	}

	@Override
	public void tension(Battle battle, ActionInfo info) {
		super.tension(battle, info);
		battle.getPlayer().setItem(new 薬草());
	}
}

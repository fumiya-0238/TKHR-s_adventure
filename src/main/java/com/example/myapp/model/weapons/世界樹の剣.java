package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 世界樹の剣 extends Weapon {
	private void getLeaf(Battle battle, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(1));
		info.addMessages("薬草を手に入れた");
	}

	@Override
	public void attack(Battle battle, List<ActionInfo> infos, int n) {
		super.attack(battle, infos, n);
		getLeaf(battle, infos, n);
	}

	@Override
	public void weekAttack(Battle battle, List<ActionInfo> infos, int n) {
		super.weekAttack(battle, infos, n);
		getLeaf(battle, infos, n);
	}

	@Override
	public void criticalAttack(Battle battle, List<ActionInfo> infos, int n) {
		//super.criticalAttack(battle, infos, n);
		Player player = battle.getPlayer();
		int kusa = 0;
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			if (item.getId() == 1) {
				player.removeItem(battle, i);
				i--;
				kusa++;
			}
		}
		infos.get(n).setDamage((int) ((player.getAttack() + kusa * 5) * 1.5));
		getLeaf(battle, infos, n);
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos, int n) {
		super.defence(battle, infos, n);
		getLeaf(battle, infos, n);
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos, int n) {
		super.tension(battle, infos, n);
		getLeaf(battle, infos, n);
	}
}

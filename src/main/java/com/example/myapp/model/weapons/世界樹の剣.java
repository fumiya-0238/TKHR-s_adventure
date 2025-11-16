package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.model.items.Item;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class 世界樹の剣 extends Weapon {
	private void getLeaf(Battle battle, List<ActionInfo> infos) {
		//ActionInfo info = new ActionInfo();
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(1));
		//battle.addMessages("薬草を手に入れた");
		//infos.add(info);
	}

	@Override
	public int attack(Battle battle, List<ActionInfo> infos) {	
		getLeaf(battle, infos);
		return super.attack(battle, infos);
	}

	@Override
	public int weekAttack(Battle battle, List<ActionInfo> infos) {
		getLeaf(battle, infos);
		return super.weekAttack(battle, infos);
	}

	@Override
	public int criticalAttack(Battle battle, List<ActionInfo> infos) {
		Player player = battle.getPlayer();
		int kusa = 0;
		for (int i = 0; i < player.getItems().size(); i++) {
			Item item = player.getItems().get(i);
			if (item.getId() == 1) {
				battle.addRemoveItemList(item);
				kusa++;
			}
		}
		getLeaf(battle, infos);
		battle.clearItemList();
		return kusa * 5;
	}

	@Override
	public void defence(Battle battle, List<ActionInfo> infos) {
		super.defence(battle, infos);
		getLeaf(battle, infos);
	}

	@Override
	public void tension(Battle battle, List<ActionInfo> infos) {
		super.tension(battle, infos);
		getLeaf(battle, infos);
	}
}

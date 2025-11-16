package com.example.myapp.model.weapons;

import java.util.List;

import com.example.myapp.creater.ConditionEnum;
import com.example.myapp.creater.CreateItem;
import com.example.myapp.model.Player;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アイテムフォース extends Weapon {
	@Override
	public void battleStart(Battle battle,List<ActionInfo> infos) {
		ActionInfo info = new ActionInfo();
		info.addMessages("挑発を手に入れた");
		info.addMessages("ゴールドチャンスを手に入れた");
		infos.add(info);
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(8));
		battle.getPlayer().setItem(CreateItem.INSTANCE.create(19));
	}

	@Override
	public void equip(Player player) {
		player.eternalCondition(ConditionEnum.アイテムヒール);
	}

	@Override
	public void takeOff(Player player) {
		player.removeCondition(ConditionEnum.アイテムヒール);
	}
}

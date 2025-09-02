package com.example.myapp.model.conditions.infinity;

import java.util.List;

import com.example.myapp.model.Living;
import com.example.myapp.model.conditions.Condition;
import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.Battle;

public class アイテムヒール extends Condition {
	@Override
	public void useItem(Battle battle, Living living, List<ActionInfo> infos, int n) {
		ActionInfo info = new ActionInfo();
		info.addMessages(name + "の効果が発動");
		info.setDamage(3);
		living.calcHeal(battle, infos, n);
	}
}

package com.example.myapp.model.items;

import java.util.List;

import com.example.myapp.repository.ActionInfo;
import com.example.myapp.repository.ActionTypeEnum;
import com.example.myapp.repository.Battle;

public class I36生命の風 extends Item {
	@Override
	public void use(Battle battle, List<ActionInfo> infos) {
		// TODO 自動生成されたメソッド・スタブ
		int damage = 30 - battle.getPlayer().getItemUse() * 7;
		if (damage > 0) {
			battle.getCommonEffect().commonHeal(battle.getPlayer(), damage, infos);
			battle.getCommonEffect().commonHeal(battle.getTargetMonster(), damage, infos);
		} else if (damage < 0) {
			battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, battle.getPlayer(), -damage, true,
					infos);
			battle.getCommonEffect().commonDamege(battle, ActionTypeEnum.ダメージ, battle.getTargetMonster(), -damage, true,
					infos);
		}
	}

}
